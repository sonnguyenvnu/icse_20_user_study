/** 
 * Helper method to convert a  {@link ListenableFuture} to an {@link AsyncFuture}.
 */
public static <T>AsyncFuture<T> bind(AsyncFramework async,ListenableFuture<T> source){
  final ResolvableFuture<T> target=async.future();
  Futures.addCallback(source,new FutureCallback<T>(){
    @Override public void onSuccess(    T result){
      target.resolve(result);
    }
    @Override public void onFailure(    Throwable t){
      target.fail(t);
    }
  }
);
  target.onCancelled(() -> {
    source.cancel(false);
  }
);
  return target;
}
