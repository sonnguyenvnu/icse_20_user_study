@Override public Observable<R> apply(Maybe<T> t){
  return new MaybeFlatMapSignalObservable<T,R>(t,onSuccessHandler,onErrorHandler,onCompleteHandler);
}
