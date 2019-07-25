@Override public Observable<R> apply(Completable t){
  return new CompletableFlatMapSignalObservable<R>(t,onCompleteHandler,onErrorHandler);
}
