/** 
 * Returns a  {@link Statement}: if  {@code method}'s  {@code @Test} annotationhas the  {@code timeout} attribute, throw an exception if {@code next}takes more than the specified number of milliseconds.
 * @deprecated
 */
@Deprecated protected Statement withPotentialTimeout(FrameworkMethod method,Object test,Statement next){
  long timeout=getTimeout(method.getAnnotation(Test.class));
  if (timeout <= 0) {
    return next;
  }
  return FailOnTimeout.builder().withTimeout(timeout,TimeUnit.MILLISECONDS).build(next);
}
