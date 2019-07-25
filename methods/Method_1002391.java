@Override public void start(Callback<None> callback){
  LOG.info("D2 WarmUp enabled");
  _loadBalancer.start(new Callback<None>(){
    @Override public void onError(    Throwable e){
      callback.onError(e);
    }
    @Override public void onSuccess(    None result){
      _executorService.execute(() -> warmUpServices(callback));
    }
  }
);
}
