/** 
 * Bind a given future as an observeable. It can also be considered as converting a future into an observable. <p> The end states of the observable will be passed on to the future.
 * @param future
 * @param transform Transforms each observed action into a future. This is equivalent to the{@link #observe(Object)} method.
 * @return An observer bound to the given future.
 */
static <T>AsyncObserver<T> bind(final ResolvableFuture<Void> future,final Function<T,AsyncFuture<Void>> transform){
  return new AsyncObserver<T>(){
    @Override public AsyncFuture<Void> observe(    T value){
      return transform.apply(value);
    }
    @Override public void cancel(){
      future.cancel();
    }
    @Override public void fail(    Throwable cause){
      future.fail(cause);
    }
    @Override public void end(){
      future.resolve(null);
    }
  }
;
}
