@Override public Future<?> apply(final ClientRequest request,final AsyncConnectorCallback callback){
  return MoreExecutors.sameThreadExecutor().submit(new Runnable(){
    @Override public void run(){
      try {
        callback.response(apply(request));
      }
 catch (      final Throwable t) {
        callback.failure(t);
      }
    }
  }
);
}
