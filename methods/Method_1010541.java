@Nullable public Element save(@NotNull ProjectDescriptor descriptor){
  Element project=new Element("project");
  Element projectModules=new Element(PROJECT_MODULES_TAG);
  for (  ModulePath path : Sequence.fromIterable(((Iterable<ModulePath>)descriptor.getModulePaths())).sort(new ISelector<ModulePath,String>(){
    public String select(    ModulePath p){
      return shrinkPath(p);
    }
  }
,true)) {
    XmlUtil.tagWithAttributes(projectModules,MODULE_PATH_TAG,PATH_TAG,shrinkPath(path),FOLDER_TAG,path.getVirtualFolder());
  }
  project.addContent(projectModules);
  return project;
}
