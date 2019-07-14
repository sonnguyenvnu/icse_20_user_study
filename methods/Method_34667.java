/** 
 * Used for asynchronous execution. <p> This will queue up the command and return a Future to get the result once it completes.
 * @return ResponseTypeResult of  {@link HystrixCommand}{@code <BatchReturnType>} execution after passing through {@link #mapResponseToRequests} to transform the {@code <BatchReturnType>} into{@code <ResponseType>}
 * @throws HystrixRuntimeException within an <code>ExecutionException.getCause()</code> (thrown by  {@link Future#get}) if an error occurs and a fallback cannot be retrieved
 */
public Future<ResponseType> queue(){
  return toObservable().toBlocking().toFuture();
}
