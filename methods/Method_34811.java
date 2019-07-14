/** 
 * DEPRECATED: Change usages of this to  {@link #onError}. Invoked after failed completion of  {@link HystrixCommand} execution.
 * @param commandInstance The executing HystrixCommand instance.
 * @param failureType {@link FailureType} representing the type of failure that occurred.<p> See  {@link HystrixRuntimeException} for more information.
 * @param e Exception thrown by  {@link HystrixCommand}
 * @return Exception that can be decorated, replaced or just returned as a pass-thru.
 * @since 1.2
 */
@Deprecated public <T>Exception onError(HystrixCommand<T> commandInstance,FailureType failureType,Exception e){
  return e;
}
