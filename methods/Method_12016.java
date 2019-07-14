/** 
 * Returns a  {@link Statement}: if  {@code method}'s  {@code @Test} annotationhas the  {@link Test#expected()} attribute, return normally only if {@code next}throws an exception of the correct type, and throw an exception otherwise.
 */
protected Statement possiblyExpectingExceptions(FrameworkMethod method,Object test,Statement next){
  Test annotation=method.getAnnotation(Test.class);
  Class<? extends Throwable> expectedExceptionClass=getExpectedException(annotation);
  return expectedExceptionClass != null ? new ExpectException(next,expectedExceptionClass) : next;
}
