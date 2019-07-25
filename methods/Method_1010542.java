@NotNull public ProjectDescriptor load(@Nullable Element root){
  final String name=myBaseDir.getName();
  ProjectDescriptor descriptor=new ProjectDescriptor(name);
  if (root == null) {
    return descriptor;
  }
  ProjectDescriptor result_jnk9az_a3a71=descriptor;
  List<Element> moduleList=ListSequence.fromList(new ArrayList<Element>());
  ListSequence.fromList(moduleList).addSequence(Sequence.fromIterable(XmlUtil.children(XmlUtil.first(root,"projectSolutions"),"solutionPath")));
  ListSequence.fromList(moduleList).addSequence(Sequence.fromIterable(XmlUtil.children(XmlUtil.first(root,"projectLanguages"),"languagePath")));
  ListSequence.fromList(moduleList).addSequence(Sequence.fromIterable(XmlUtil.children(XmlUtil.first(root,"projectDevkits"),"devkitPath")));
  ListSequence.fromList(moduleList).addSequence(Sequence.fromIterable(XmlUtil.children(XmlUtil.first(root,PROJECT_MODULES_TAG),MODULE_PATH_TAG)));
  for (  Element moduleElement : ListSequence.fromList(moduleList)) {
    String path=myMacroHelper.expandPath(moduleElement.getAttributeValue(PATH_TAG));
    String virtualFolder=moduleElement.getAttributeValue(FOLDER_TAG);
    ModulePath modulePath=new ModulePath(path,virtualFolder);
    result_jnk9az_a3a71.addModulePath(modulePath);
  }
  return descriptor;
}
