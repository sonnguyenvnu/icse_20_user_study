@Override public void unregister(@Nullable IComponentHostInterceptor interceptor){
  if (interceptor == null) {
    return;
  }
  isInterceptorListHaveChange=true;
  Map<String,Class<? extends RouterInterceptor>> childInterceptorMap=interceptor.getInterceptorMap();
  if (childInterceptorMap != null) {
    for (    Map.Entry<String,Class<? extends RouterInterceptor>> entry : childInterceptorMap.entrySet()) {
      mInterceptorMap.remove(entry.getKey());
      RouterInterceptorCache.removeCache(entry.getValue());
    }
  }
}
