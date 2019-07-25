@Override public Maybe<R> apply(Completable t){
  return new CompletableFlatMapSignalMaybe<R>(t,onCompleteHandler,onErrorHandler);
}
