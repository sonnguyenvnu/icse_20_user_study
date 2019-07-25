@Override public void register(@Nullable IComponentHostInterceptor interceptor){
  if (interceptor == null) {
    return;
  }
  isInterceptorListHaveChange=true;
  moduleInterceptorMap.put(interceptor.getHost(),interceptor);
  Map<String,Class<? extends RouterInterceptor>> childInterceptorMap=interceptor.getInterceptorMap();
  if (childInterceptorMap != null) {
    mInterceptorMap.putAll(childInterceptorMap);
  }
}
