public void read(Element root){
  Element misc=root.getChild(ROOT_TASKDATA);
  myWorker=misc.getAttributeValue(PROP_WORKER);
  myFailOnError=Boolean.parseBoolean(misc.getAttributeValue(PROP_FAILONERROR));
  int logLevelInt=Integer.parseInt(misc.getAttributeValue(PROP_LOGLEVEL));
  myLogLevel=Level.toLevel(logLevelInt,Level.INFO);
  myLoadBootstrapLibraries=Boolean.parseBoolean(misc.getAttributeValue(PROP_LOADBOOTSTRAPLIBRARIES));
  for (  Element e : root.getChildren(ELEM_LIBRARIES)) {
    for (    Element lib : e.getChildren(ELEM_LIBRARY)) {
      File file=new File(lib.getAttributeValue(PATH));
      addLibrary(lib.getAttributeValue(NAME),file);
    }
    for (    Element lib : e.getChildren(ELEM_LIBRARYJAR)) {
      addLibraryJar(lib.getAttributeValue(PATH));
    }
  }
  for (  Element e : root.getChildren(ELEM_REPO)) {
    RepositoryDescriptor repo=new RepositoryDescriptor();
    for (    Element f : e.getChildren(ELEM_REPO_FOLDER)) {
      repo.folders.add(f.getAttributeValue(PATH));
    }
    for (    Element f : e.getChildren(ELEM_REPO_MODULEFILE)) {
      repo.files.add(f.getAttributeValue(PATH));
    }
    setRepo(repo);
  }
  for (  Element e : root.getChildren(ELEM_MACRO)) {
    addMacro(e.getAttributeValue(NAME),e.getAttributeValue(VALUE));
  }
  for (  Element e : root.getChildren(ELEM_PLUGIN)) {
    addPlugin(new PluginData(e.getAttributeValue(PATH),e.getAttributeValue(ID)));
  }
  for (  Element e : root.getChildren(ELEM_PROPERTY)) {
    addProperty(e.getAttributeValue(NAME),e.getAttributeValue(VALUE));
  }
}
