private <T>AsyncFuture<T> convert(final ListenableFuture<T> request){
  final ResolvableFuture<T> future=async.future();
  Futures.addCallback(request,new FutureCallback<T>(){
    @Override public void onSuccess(    T result){
      future.resolve(result);
    }
    @Override public void onFailure(    Throwable t){
      future.fail(t);
    }
  }
);
  return future;
}
