private void putReponseToCache(String key,TLObject response){
  CachedResult cachedResult=responseCache.get(key);
  if (cachedResult == null) {
    cachedResult=new CachedResult();
    cachedResult.response=response;
    cachedResult.firstQueryTime=SystemClock.uptimeMillis();
    responseCache.put(key,cachedResult);
  }
  cachedResult.lastQueryTime=SystemClock.uptimeMillis();
}
