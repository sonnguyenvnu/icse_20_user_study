/** 
 * Invoked when the fallback method in  {@link HystrixInvokable} fails with an Exception.
 * @param commandInstance The executing HystrixInvokable instance.
 * @param e exception object
 * @since 1.2
 */
public <T>Exception onFallbackError(HystrixInvokable<T> commandInstance,Exception e){
  return e;
}
