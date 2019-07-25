@Override public Single<R> apply(Maybe<T> t){
  return new MaybeFlatMapSignalSingle<T,R>(t,onSuccessHandler,onErrorHandler,onCompleteHandler);
}
