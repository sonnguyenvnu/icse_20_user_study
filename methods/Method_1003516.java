@Override public <T>Publisher<T> execute(ClientCallback<Publisher<T>> callback){
  return Flux.defer(() -> callback.doWithClient(getClient())).onErrorMap(this::translateException);
}
