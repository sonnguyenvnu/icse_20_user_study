@Override public void addCacheData(final String cachePath){
  TreeCache cache=new TreeCache(client,cachePath);
  try {
    cache.start();
  }
 catch (  final Exception ex) {
    RegExceptionHandler.handleException(ex);
  }
  caches.put(cachePath + "/",cache);
}
