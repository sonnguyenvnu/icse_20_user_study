/** 
 * Collects all interceptors.
 */
protected void collectActionInterceptors(){
  final Collection<? extends ActionInterceptor> interceptorValues=interceptorsManager.getAllInterceptors();
  interceptors=new ArrayList<>();
  interceptors.addAll(interceptorValues);
  interceptors.sort(Comparator.comparing(a -> a.getClass().getSimpleName()));
}
