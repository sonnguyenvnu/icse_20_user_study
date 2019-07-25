@Override public <T>T get(String cacheName,Object key,IDataLoader dataLoader){
  Cache cache=getCache(cacheName);
  CaffeineCacheObject data=(CaffeineCacheObject)cache.getIfPresent(key);
  if (data == null || data.isDue()) {
    Object newValue=dataLoader.load();
    if (newValue != null) {
      data=new CaffeineCacheObject(newValue);
      putData(cache,key,data);
    }
    return (T)newValue;
  }
 else {
    return (T)data.getValue();
  }
}
