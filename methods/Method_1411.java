private Task<Boolean> containsAsync(final CacheKey key){
  try {
    return Task.call(new Callable<Boolean>(){
      @Override public Boolean call() throws Exception {
        return checkInStagingAreaAndFileCache(key);
      }
    }
,mReadExecutor);
  }
 catch (  Exception exception) {
    FLog.w(TAG,exception,"Failed to schedule disk-cache read for %s",key.getUriString());
    return Task.forError(exception);
  }
}
