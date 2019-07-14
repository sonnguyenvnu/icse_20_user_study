/** 
 * DEPRECATED: Change usages of this to  {@link #onFallbackError}. Invoked after failed execution of  {@link HystrixCommand#getFallback()} with thrown exception.
 * @param commandInstance The executing HystrixCommand instance.
 * @param e Exception thrown by  {@link HystrixCommand#getFallback()}
 * @return Exception that can be decorated, replaced or just returned as a pass-thru.
 * @since 1.2
 */
@Deprecated public <T>Exception onFallbackError(HystrixCommand<T> commandInstance,Exception e){
  return e;
}
