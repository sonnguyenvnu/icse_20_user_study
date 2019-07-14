/** 
 * Adds to  {@code errors} a throwable for each problem detected. Looks for{@code BeforeClass},  {@code AfterClass},  {@code Before} and {@code After}annotations.
 * @param method the method that is being validated
 * @return A list of exceptions detected
 * @since 4.12
 */
@Override public List<Exception> validateAnnotatedMethod(FrameworkMethod method){
  List<Exception> errors=new ArrayList<Exception>();
  Annotation[] annotations=method.getAnnotations();
  for (  Annotation annotation : annotations) {
    for (    Class<?> clazz : INCOMPATIBLE_ANNOTATIONS) {
      if (annotation.annotationType().isAssignableFrom(clazz)) {
        addErrorMessage(errors,clazz);
      }
    }
  }
  return unmodifiableList(errors);
}
