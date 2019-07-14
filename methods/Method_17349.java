@Override @SuppressWarnings("PMD.CloseResource") public void close(URI uri,ClassLoader classLoader){
synchronized (cacheManagers) {
    ClassLoader managerClassLoader=getManagerClassLoader(classLoader);
    Map<URI,CacheManager> cacheManagersByURI=cacheManagers.get(managerClassLoader);
    if (cacheManagersByURI != null) {
      CacheManager cacheManager=cacheManagersByURI.remove(getManagerUri(uri));
      if (cacheManager != null) {
        cacheManager.close();
      }
      if (cacheManagersByURI.isEmpty()) {
        cacheManagers.remove(managerClassLoader);
      }
    }
  }
}
