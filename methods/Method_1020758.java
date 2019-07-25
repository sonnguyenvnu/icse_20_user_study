@Override public Maybe<R> apply(Maybe<T> t){
  return new MaybeFlatMapSignalMaybe<T,R>(t,onSuccessHandler,onErrorHandler,onCompleteHandler);
}
