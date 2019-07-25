@Override public Flowable<R> apply(Completable t){
  return new CompletableFlatMapSignalFlowable<R>(t,onCompleteHandler,onErrorHandler);
}
