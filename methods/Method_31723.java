private void addClassesAndResourcesDirs(Set<URL> extraURLs) throws IllegalAccessException, InvocationTargetException, MalformedURLException {
  JavaPluginConvention plugin=getProject().getConvention().getPlugin(JavaPluginConvention.class);
  for (  SourceSet sourceSet : plugin.getSourceSets()) {
    try {
      @SuppressWarnings("JavaReflectionMemberAccess") Method getClassesDirs=SourceSetOutput.class.getMethod("getClassesDirs");
      FileCollection classesDirs=(FileCollection)getClassesDirs.invoke(sourceSet.getOutput());
      for (      File directory : classesDirs.getFiles()) {
        URL classesUrl=directory.toURI().toURL();
        getLogger().debug("Adding directory to Classpath: " + classesUrl);
        extraURLs.add(classesUrl);
      }
    }
 catch (    NoSuchMethodException e) {
      URL classesUrl=sourceSet.getOutput().getClassesDir().toURI().toURL();
      getLogger().debug("Adding directory to Classpath: " + classesUrl);
      extraURLs.add(classesUrl);
    }
    URL resourcesUrl=sourceSet.getOutput().getResourcesDir().toURI().toURL();
    getLogger().debug("Adding directory to Classpath: " + resourcesUrl);
    extraURLs.add(resourcesUrl);
  }
}
