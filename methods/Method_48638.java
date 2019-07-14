public void close(){
  cache.invalidateAll();
  cache.cleanUp();
}
