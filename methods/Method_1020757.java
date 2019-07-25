@Override public Flowable<R> apply(Maybe<T> t){
  return new MaybeFlatMapSignalFlowable<T,R>(t,onSuccessHandler,onErrorHandler,onCompleteHandler);
}
