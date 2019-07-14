/** 
 * Reads class or method annotation for action interceptors.
 */
protected Class<? extends ActionInterceptor>[] readActionInterceptors(final AnnotatedElement actionClassOrMethod){
  Class<? extends ActionInterceptor>[] result=null;
  InterceptedBy interceptedBy=actionClassOrMethod.getAnnotation(InterceptedBy.class);
  if (interceptedBy != null) {
    result=interceptedBy.value();
    if (result.length == 0) {
      result=null;
    }
  }
  return result;
}
