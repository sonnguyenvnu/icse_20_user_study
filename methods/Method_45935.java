@Override protected void putDeserializerToCachedType(String type,Deserializer deserializer){
  ConcurrentMap<ClassLoader,Deserializer> concurrentMap=cachedTypeDeserializerMap.get(type);
  if (concurrentMap == null) {
    ConcurrentMap<ClassLoader,Deserializer> newMap=new ConcurrentHashMap<ClassLoader,Deserializer>();
    concurrentMap=cachedTypeDeserializerMap.putIfAbsent(type,newMap);
    if (concurrentMap == null) {
      concurrentMap=newMap;
    }
  }
  concurrentMap.put(Thread.currentThread().getContextClassLoader(),deserializer);
}
