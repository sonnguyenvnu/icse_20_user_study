@NotNull public Element save(@NotNull LanguageDescriptor descriptor){
  Element languageElement=new Element("language");
  languageElement.setAttribute("namespace",descriptor.getNamespace());
  if (descriptor.getId() != null) {
    languageElement.setAttribute("uuid",descriptor.getId().toString());
  }
  if (descriptor.getGenPath() != null) {
    String p=myMacroHelper.shrinkPath(descriptor.getGenPath());
    if (!(SOURCE_GEN_DEFAULT.equals(p))) {
      languageElement.setAttribute("generatorOutputPath",p);
    }
  }
  languageElement.setAttribute("languageVersion",Integer.toString(descriptor.getLanguageVersion()));
  languageElement.setAttribute("moduleVersion",Integer.toString(descriptor.getModuleVersion()));
  Element models=new Element("models");
  ModuleDescriptorPersistence.saveModelRoots(models,descriptor.getModelRootDescriptors(),myMacroHelper);
  languageElement.addContent(models);
  if (!(descriptor.getModuleFacetDescriptors().isEmpty())) {
    Element facets=new Element("facets");
    ModuleDescriptorPersistence.saveFacets(facets,descriptor.getModuleFacetDescriptors(),myMacroHelper);
    languageElement.addContent(facets);
  }
  Element accessoryModels=new Element("accessoryModels");
  for (  SModelReference model : SetSequence.fromSet(descriptor.getAccessoryModels())) {
    XmlUtil.tagWithAttribute(accessoryModels,"model","modelUID",model.toString());
  }
  languageElement.addContent(accessoryModels);
  if (!(descriptor.getGenerators().isEmpty())) {
    Element generators=new Element("generators");
    GeneratorDescriptorPersistence gdp=new GeneratorDescriptorPersistence(myMacroHelper,true);
    for (    GeneratorDescriptor generatorDescriptor : ListSequence.fromList(descriptor.getGenerators())) {
      generators.addContent(gdp.save(generatorDescriptor));
    }
    languageElement.addContent(generators);
  }
  if (!(descriptor.getJavaLibs().isEmpty())) {
    Element stubModelEntries=new Element("stubModelEntries");
    ModuleDescriptorPersistence.saveStubModelEntries(stubModelEntries,(Collection<String>)descriptor.getJavaLibs(),myMacroHelper);
    languageElement.addContent(stubModelEntries);
  }
  Element sourcePath=new Element("sourcePath");
  for (  String p : descriptor.getSourcePaths()) {
    XmlUtil.tagWithAttribute(sourcePath,"source","path",myMacroHelper.shrinkPath(p));
  }
  languageElement.addContent(sourcePath);
  ModuleDescriptorPersistence.saveDependencies(languageElement,descriptor);
  Element extendedLanguages=new Element("extendedLanguages");
  for (  SModuleReference ref : SetSequence.fromSet(descriptor.getExtendedLanguages())) {
    XmlUtil.tagWithText(extendedLanguages,"extendedLanguage",ref.toString());
  }
  languageElement.addContent(extendedLanguages);
  return languageElement;
}
