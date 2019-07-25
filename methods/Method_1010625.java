public void write(Element root){
  Element misc=new Element(ROOT_TASKDATA);
  if (myWorker != null) {
    misc.setAttribute(PROP_WORKER,myWorker);
  }
  misc.setAttribute(PROP_FAILONERROR,Boolean.toString(myFailOnError));
  misc.setAttribute(PROP_LOGLEVEL,Integer.toString(myLogLevel.toInt()));
  misc.setAttribute(PROP_LOADBOOTSTRAPLIBRARIES,Boolean.toString(myLoadBootstrapLibraries));
  root.addContent(misc);
  if (!(myLibraries.isEmpty()) || !(myLibraryJars.isEmpty())) {
    Element libraries=new Element(ELEM_LIBRARIES);
    for (    String key : myLibraries.keySet()) {
      libraries.addContent(new Element(ELEM_LIBRARY).setAttribute(NAME,key).setAttribute(PATH,myLibraries.get(key).getAbsolutePath()));
    }
    for (    String jar : myLibraryJars) {
      libraries.addContent(new Element(ELEM_LIBRARYJAR).setAttribute(PATH,jar));
    }
    root.addContent(libraries);
  }
  if (myRepo != null) {
    Element repo=new Element(ELEM_REPO);
    for (    String f : myRepo.folders) {
      repo.addContent(new Element(ELEM_REPO_FOLDER).setAttribute(PATH,f));
    }
    for (    String f : myRepo.files) {
      repo.addContent(new Element(ELEM_REPO_MODULEFILE).setAttribute(PATH,f));
    }
    root.addContent(repo);
  }
  for (  String key : myMacros.keySet()) {
    root.addContent(new Element(ELEM_MACRO).setAttribute(NAME,key).setAttribute(VALUE,myMacros.get(key)));
  }
  for (  PluginData p : myPlugins) {
    root.addContent(new Element(ELEM_PLUGIN).setAttribute(PATH,p.path).setAttribute(ID,(p.id == null ? "" : p.id)));
  }
  for (  String key : myProperties.keySet()) {
    root.addContent(new Element(ELEM_PROPERTY).setAttribute(NAME,key).setAttribute(VALUE,myProperties.get(key)));
  }
}
