static ClassLoader getParentClassLoader(){
  ClassLoader contextClassLoader=Thread.currentThread().getContextClassLoader();
  if (contextClassLoader != null) {
    try {
      contextClassLoader.loadClass(JSON.class.getName());
      return contextClassLoader;
    }
 catch (    ClassNotFoundException e) {
    }
  }
  return JSON.class.getClassLoader();
}
