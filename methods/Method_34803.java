/** 
 * Invoked when the user-defined execution method in  {@link HystrixInvokable} fails with an Exception.
 * @param commandInstance The executing HystrixInvokable instance.
 * @param e exception object
 * @since 1.4
 */
public <T>Exception onExecutionError(HystrixInvokable<T> commandInstance,Exception e){
  return e;
}
