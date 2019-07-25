@Override public CompletableSource apply(Completable upstream){
  return new CompletableFlatMapSignalCompletable(upstream,onCompleteHandler,onErrorHandler);
}
