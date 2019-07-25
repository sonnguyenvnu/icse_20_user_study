public List<CommandArgs> build(){
  File schemaFile=null;
  if (schemaFilePath != null && !schemaFilePath.trim().isEmpty()) {
    schemaFile=Paths.get(schemaFilePath).toFile();
    if (!schemaFile.exists()) {
      schemaFile=Paths.get(task.getProject().getProjectDir().getAbsolutePath(),schemaFilePath).toFile();
    }
    if (!schemaFile.exists()) {
      throw new GradleException("Provided schema file path doesn't exists: " + schemaFilePath + ". Please ensure a valid schema file exists");
    }
  }
  File targetPackageFolder=null;
  if (schemaFile != null) {
    if (outputPackageName == null || outputPackageName.trim().isEmpty()) {
      throw new GradleException("Missing explicit outputPackageName option. Please ensure a valid package name is provided");
    }
 else {
      targetPackageFolder=new File(outputFolder.getAbsolutePath() + File.separator + "src" + File.separator + "main" + File.separator + "graphql" + File.separator + outputPackageName.replace(".",File.separator));
    }
  }
  final List<ApolloCodegenIRArgs> codegenArgs;
  if (schemaFile == null) {
    codegenArgs=codeGenArgs(task.getInputs().getSourceFiles().getFiles());
  }
 else {
    Set<String> queryFilePaths=new HashSet<>();
    for (    File queryFile : queryFilesFrom(task.getInputs().getSourceFiles().getFiles())) {
      queryFilePaths.add(queryFile.getAbsolutePath());
    }
    codegenArgs=Collections.singletonList(new ApolloCodegenIRArgs(schemaFile,queryFilePaths,targetPackageFolder));
  }
  List<CommandArgs> taskExecutionArgs=new ArrayList<>();
  for (  ApolloCodegenIRArgs codegenArg : codegenArgs) {
    codegenArg.irOutputFolder.mkdirs();
    List<String> args=new ArrayList<>();
    args.add("generate");
    args.addAll(codegenArg.queryFilePaths);
    args.addAll(Arrays.asList("--add-typename","--schema",codegenArg.schemaFile.getAbsolutePath(),"--output",codegenArg.irOutputFolder.getAbsolutePath() + File.separator + Utils.capitalize(variant) + "API.json","--operation-ids-path",codegenArg.irOutputFolder.getAbsolutePath() + File.separator + Utils.capitalize(variant) + "OperationIdMap.json","--merge-in-fields-from-fragment-spreads","false","--target","json"));
    taskExecutionArgs.add(new CommandArgs(args));
  }
  return taskExecutionArgs;
}
