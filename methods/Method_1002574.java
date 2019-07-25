public static GeneratorResult run(String resolverPath,String defaultPackage,String rootPath,final boolean generateImported,final boolean generateDataTemplates,RestliVersion version,RestliVersion deprecatedByVersion,String targetDirectoryPath,String[] sources) throws IOException {
  final RestSpecParser parser=new RestSpecParser();
  final JavaRequestBuilderGenerator generator=new JavaRequestBuilderGenerator(resolverPath,defaultPackage,generateDataTemplates,version,deprecatedByVersion,rootPath);
  final ClassLoader classLoader=JavaCodeUtil.classLoaderFromResolverPath(resolverPath);
  final RestSpecParser.ParseResult parseResult=parser.parseSources(sources);
  final StringBuilder message=new StringBuilder();
  for (  CodeUtil.Pair<ResourceSchema,File> pair : parseResult.getSchemaAndFiles()) {
    ResourceSchema resourceSchema=pair.first;
    if (resourceSchema != null && ResourceEntityType.UNSTRUCTURED_DATA == resourceSchema.getEntityType()) {
      continue;
    }
    try {
      final JDefinedClass clazz=generator.generate(resourceSchema,pair.second,rootPath);
    }
 catch (    Exception e) {
      message.append(e.getMessage() + "\n");
    }
  }
  if (message.length() > 0) {
    throw new IOException(message.toString());
  }
  final PegasusDataTemplateGenerator.DataTemplatePersistentClassChecker dataTemplateChecker=new PegasusDataTemplateGenerator.DataTemplatePersistentClassChecker(generateImported,generator.getSpecGenerator(),generator.getJavaDataTemplateGenerator(),Collections.<File>emptySet());
  final JavaCodeUtil.PersistentClassChecker checker=new JavaCodeUtil.PersistentClassChecker(){
    @Override public boolean isPersistent(    JDefinedClass clazz){
      if (generateDataTemplates || generator.isGeneratedArrayClass(clazz)) {
        try {
          Class.forName(clazz.fullName(),false,classLoader);
        }
 catch (        ClassNotFoundException e) {
          return true;
        }
      }
      return dataTemplateChecker.isPersistent(clazz);
    }
  }
;
  final JCodeModel requestBuilderCodeModel=generator.getCodeModel();
  final JCodeModel dataTemplateCodeModel=generator.getJavaDataTemplateGenerator().getCodeModel();
  final File targetDirectory=new File(targetDirectoryPath);
  final List<File> targetFiles=JavaCodeUtil.targetFiles(targetDirectory,requestBuilderCodeModel,classLoader,checker);
  targetFiles.addAll(JavaCodeUtil.targetFiles(targetDirectory,dataTemplateCodeModel,classLoader,checker));
  final List<File> modifiedFiles;
  if (FileUtil.upToDate(parseResult.getSourceFiles(),targetFiles)) {
    modifiedFiles=Collections.emptyList();
    _log.info("Target files are up-to-date: " + targetFiles);
  }
 else {
    modifiedFiles=targetFiles;
    _log.info("Generating " + targetFiles.size() + " files");
    _log.debug("Files: " + targetFiles);
    requestBuilderCodeModel.build(new FileCodeWriter(targetDirectory,true));
    dataTemplateCodeModel.build(new FileCodeWriter(targetDirectory,true));
  }
  return new DefaultGeneratorResult(parseResult.getSourceFiles(),targetFiles,modifiedFiles);
}
