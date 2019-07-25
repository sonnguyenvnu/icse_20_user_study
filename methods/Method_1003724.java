/** 
 * Sends the result of the future downstream.
 * @param future the future to consume the value of
 */
default void accept(ListenableFuture<? extends T> future){
  Futures.addCallback(future,new FutureCallback<T>(){
    @Override public void onSuccess(    T result){
      success(result);
    }
    @Override public void onFailure(    Throwable t){
      error(t);
    }
  }
,Execution.current().getEventLoop());
}
