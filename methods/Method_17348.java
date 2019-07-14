@Override @SuppressWarnings("PMD.CloseResource") public void close(ClassLoader classLoader){
synchronized (cacheManagers) {
    ClassLoader managerClassLoader=getManagerClassLoader(classLoader);
    Map<URI,CacheManager> cacheManagersByURI=cacheManagers.remove(managerClassLoader);
    if (cacheManagersByURI != null) {
      for (      CacheManager cacheManager : cacheManagersByURI.values()) {
        cacheManager.close();
      }
    }
  }
}
