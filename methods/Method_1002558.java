@Override public ResourceMethodConfig apply(ResourceMethodDescriptor requestMethod){
  ResourceMethodConfigCacheKey cacheKey=new ResourceMethodConfigCacheKey(requestMethod);
  return _cache.computeIfAbsent(cacheKey,this::resolve);
}
