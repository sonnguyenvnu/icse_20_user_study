@Override public long create(Object obj){
  testAvailable();
  Class clz=obj.getClass();
  Parsed parsed=Parser.get(clz);
  long id=dataTransform.create(obj);
  if (!isNoCache() && !parsed.isNoCache())   cacheResolver.markForRefresh(clz);
  return id;
}
