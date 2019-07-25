protected <T extends ActionResponse>AsyncFuture<T> bind(final ListenableActionFuture<T> actionFuture){
  final ResolvableFuture<T> future=async.future();
  actionFuture.addListener(new ActionListener<T>(){
    @Override public void onResponse(    T result){
      future.resolve(result);
    }
    @Override public void onFailure(    Exception e){
      future.fail(e);
    }
  }
);
  return future;
}
