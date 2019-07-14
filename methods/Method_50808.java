private ResourceLoader setupResourceLoader(){
  if (classpath == null) {
    classpath=new Path(project);
  }
  classpath.add(new Path(null,project.getBaseDir().toString()));
  project.log("Using the AntClassLoader: " + classpath,Project.MSG_VERBOSE);
  final boolean parentFirst=true;
  return new ResourceLoader(new AntClassLoader(Thread.currentThread().getContextClassLoader(),project,classpath,parentFirst));
}
