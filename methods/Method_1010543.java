@NotNull public Element save(SolutionDescriptor descriptor){
  Element result=new Element("solution");
  if (descriptor.getNamespace() != null) {
    result.setAttribute("name",descriptor.getNamespace());
  }
  if (descriptor.getId() != null) {
    result.setAttribute("uuid",descriptor.getId().toString());
  }
  result.setAttribute("moduleVersion",Integer.toString(descriptor.getModuleVersion()));
  if (descriptor.getKind() != SolutionKind.NONE) {
    result.setAttribute("pluginKind",descriptor.getKind().name());
  }
  result.setAttribute(COMPILE_IN_MPS,Boolean.toString(descriptor.getCompileInMPS()));
  if (descriptor.getOutputPath() != null) {
    String p=myMacroHelper.shrinkPath(descriptor.getOutputPath());
    if (!(SOURCE_GEN_DEFAULT.equals(p))) {
      result.setAttribute("generatorOutputPath",p);
    }
  }
  if (descriptor.needsExternalIdeaCompile()) {
    result.addContent(new Element("compileInIDEA"));
  }
  Element models=new Element("models");
  ModuleDescriptorPersistence.saveModelRoots(models,descriptor.getModelRootDescriptors(),myMacroHelper);
  result.addContent(models);
  if (!(descriptor.getModuleFacetDescriptors().isEmpty())) {
    Element facets=new Element("facets");
    ModuleDescriptorPersistence.saveFacets(facets,descriptor.getModuleFacetDescriptors(),myMacroHelper);
    result.addContent(facets);
  }
  Collection<String> additionalJavaStubPaths=descriptor.getJavaLibs();
  if (!(additionalJavaStubPaths.isEmpty())) {
    Element stubModelEntries=new Element("stubModelEntries");
    ModuleDescriptorPersistence.saveStubModelEntries(stubModelEntries,additionalJavaStubPaths,myMacroHelper);
    result.addContent(stubModelEntries);
  }
  Element sourcePath=new Element(SOURCE_PATH);
  for (  String p : descriptor.getSourcePaths()) {
    XmlUtil.tagWithAttribute(sourcePath,SOURCE_PATH_SOURCE,"path",myMacroHelper.shrinkPath(p));
  }
  result.addContent(sourcePath);
  ModuleDescriptorPersistence.saveDependencies(result,descriptor);
  return result;
}
