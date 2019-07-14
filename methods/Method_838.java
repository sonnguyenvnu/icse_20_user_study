private Class<?> processOnDemand(String qualifiedName){
  for (  String entry : importedOnDemand) {
    try {
      return pmdClassLoader.loadClass(entry + "." + qualifiedName);
    }
 catch (    Throwable e) {
    }
  }
  return null;
}
