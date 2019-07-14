/** 
 * Used for synchronous execution. <p> If  {@link Scope#REQUEST} is being used then synchronous execution will only result in collapsing if other threads are running within the same scope.
 * @return ResponseTypeResult of  {@link HystrixCommand}{@code <BatchReturnType>} execution after passing through {@link #mapResponseToRequests} to transform the {@code <BatchReturnType>} into{@code <ResponseType>}
 * @throws HystrixRuntimeException if an error occurs and a fallback cannot be retrieved
 */
public ResponseType execute(){
  try {
    return queue().get();
  }
 catch (  Throwable e) {
    if (e instanceof HystrixRuntimeException) {
      throw (HystrixRuntimeException)e;
    }
    if (e.getCause() instanceof HystrixRuntimeException) {
      throw (HystrixRuntimeException)e.getCause();
    }
    String message=getClass().getSimpleName() + " HystrixCollapser failed while executing.";
    logger.debug(message,e);
    throw new RuntimeException(message,e);
  }
}
