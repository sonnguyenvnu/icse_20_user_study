/** 
 * ??????????????
 */
public void check(){
  Set<String> set=new HashSet<>();
  for (  Map.Entry<String,IComponentHostInterceptor> entry : moduleInterceptorMap.entrySet()) {
    IComponentHostInterceptor childInterceptor=entry.getValue();
    if (childInterceptor == null || childInterceptor.getInterceptorNames() == null) {
      continue;
    }
    Set<String> childInterceptorNames=childInterceptor.getInterceptorNames();
    for (    String interceptorName : childInterceptorNames) {
      if (set.contains(interceptorName)) {
        throw new InterceptorNameExistException("the interceptor's name is exist?" + interceptorName);
      }
      set.add(interceptorName);
    }
  }
}
