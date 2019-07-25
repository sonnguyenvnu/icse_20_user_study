@NonNull @Override public synchronized List<RouterInterceptor> interceptors(@NonNull Uri uri){
  final String targetUrl=getTargetUrl(uri);
  final RouterBean routerBean=routerMap.get(targetUrl);
  if (routerBean == null) {
    return Collections.emptyList();
  }
  final List<Class<? extends RouterInterceptor>> targetInterceptors=routerBean.getInterceptors();
  final List<String> targetInterceptorNames=routerBean.getInterceptorNames();
  if ((targetInterceptors == null || targetInterceptors.isEmpty()) && (targetInterceptorNames == null || targetInterceptorNames.isEmpty())) {
    return Collections.emptyList();
  }
  final List<RouterInterceptor> result=new ArrayList<>();
  if (targetInterceptors != null) {
    for (    Class<? extends RouterInterceptor> interceptorClass : targetInterceptors) {
      final RouterInterceptor interceptor=RouterInterceptorCache.getInterceptorByClass(interceptorClass);
      if (interceptor == null) {
        throw new InterceptorNotFoundException("can't find the interceptor and it's className is " + interceptorClass + ",target url is " + uri.toString());
      }
      result.add(interceptor);
    }
  }
  if (targetInterceptorNames != null) {
    for (    String interceptorName : targetInterceptorNames) {
      final RouterInterceptor interceptor=InterceptorCenter.getInstance().getByName(interceptorName);
      if (interceptor == null) {
        throw new InterceptorNotFoundException("can't find the interceptor and it's name is " + interceptorName + ",target url is " + uri.toString());
      }
      result.add(interceptor);
    }
  }
  return result;
}
