private Class<?> processOnDemand(String qualifiedName){
  for (  String entry : importedOnDemand) {
    String fullClassName=entry + "." + qualifiedName;
    try {
      return pmdClassLoader.loadClass(fullClassName);
    }
 catch (    ClassNotFoundException ignored) {
    }
catch (    LinkageError e) {
      if (LOG.isLoggable(Level.FINE)) {
        LOG.log(Level.FINE,"Tried to load class " + fullClassName + " from on demand import, " + "with an incomplete classpath.",e);
      }
    }
  }
  return null;
}
