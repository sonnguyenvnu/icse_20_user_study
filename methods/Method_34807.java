/** 
 * DEPRECATED: Change usages of this to  {@link #onExecutionError}Invoked after failed execution of  {@link HystrixCommand#run()} with thrown Exception.
 * @param commandInstance The executing HystrixCommand instance.
 * @param e Exception thrown by  {@link HystrixCommand#run()}
 * @return Exception that can be decorated, replaced or just returned as a pass-thru.
 * @since 1.2
 */
@Deprecated public <T>Exception onRunError(HystrixInvokable<T> commandInstance,Exception e){
  return e;
}
