private Object appendPropertyValue(StringBuilder cacheKeyBuilder,List<String> names,Object obj) throws HystrixCacheKeyGenerationException {
  for (  String name : names) {
    if (obj != null) {
      obj=getPropertyValue(name,obj);
    }
  }
  if (obj != null) {
    cacheKeyBuilder.append(obj);
  }
  return obj;
}
