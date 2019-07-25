/** 
 * Abort this request, and store a  {@link org.elasticsearch.action.bulk.BulkItemResponse.Failure} response.
 * @param index The concrete index that was resolved for this request
 * @param cause The cause of the rejection (may not be null)
 * @throws IllegalStateException If a response already exists for this request
 */
public void abort(String index,Exception cause){
  if (primaryResponse == null) {
    final BulkItemResponse.Failure failure=new BulkItemResponse.Failure(index,request.type(),request.id(),Objects.requireNonNull(cause),true);
    setPrimaryResponse(new BulkItemResponse(id,request.opType(),failure));
  }
 else {
    assert primaryResponse.isFailed() && primaryResponse.getFailure().isAborted() : "response [" + Strings.toString(primaryResponse) + "]; cause [" + cause + "]";
    if (primaryResponse.isFailed() && primaryResponse.getFailure().isAborted()) {
      primaryResponse.getFailure().getCause().addSuppressed(cause);
    }
 else {
      throw new IllegalStateException("aborting item that with response [" + primaryResponse + "] that was previously processed",cause);
    }
  }
}
