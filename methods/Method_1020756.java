@Override public Completable apply(Maybe<T> t){
  return new MaybeFlatMapSignalCompletable<T>(t,onSuccessHandler,onErrorHandler,onCompleteHandler);
}
