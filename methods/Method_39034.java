/** 
 * Reads method's http method or  {@code null} if not specified.
 */
private String readMethodHttpMethod(final Method actionMethod){
  for (  Class<? extends Annotation> methodAnnotation : METHOD_ANNOTATIONS) {
    if (actionMethod.getAnnotation(methodAnnotation) != null) {
      return methodAnnotation.getSimpleName();
    }
  }
  return null;
}
