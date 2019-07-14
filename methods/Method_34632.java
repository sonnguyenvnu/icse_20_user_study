/** 
 * Submit a request to a batch. If the batch maxSize is hit trigger the batch immediately.
 * @param arg argument to a {@link RequestCollapser}
 * @return Observable<ResponseType>
 * @throws IllegalStateException if submitting after shutdown
 */
public Observable<ResponseType> submitRequest(final RequestArgumentType arg){
  if (!timerListenerRegistered.get() && timerListenerRegistered.compareAndSet(false,true)) {
    timerListenerReference.set(timer.addListener(new CollapsedTask()));
  }
  while (true) {
    final RequestBatch<BatchReturnType,ResponseType,RequestArgumentType> b=batch.get();
    if (b == null) {
      return Observable.error(new IllegalStateException("Submitting requests after collapser is shutdown"));
    }
    final Observable<ResponseType> response;
    if (arg != null) {
      response=b.offer(arg);
    }
 else {
      response=b.offer((RequestArgumentType)NULL_SENTINEL);
    }
    if (response != null) {
      return response;
    }
 else {
      createNewBatchAndExecutePreviousIfNeeded(b);
    }
  }
}
