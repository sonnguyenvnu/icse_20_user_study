/** 
 * Invoked when  {@link HystrixInvokable} fails with an Exception.
 * @param commandInstance The executing HystrixInvokable instance.
 * @param failureType {@link FailureType} enum representing which type of error
 * @param e exception object
 * @since 1.2
 */
public <T>Exception onError(HystrixInvokable<T> commandInstance,FailureType failureType,Exception e){
  return e;
}
