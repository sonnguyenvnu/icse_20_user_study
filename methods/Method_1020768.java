@Override public Maybe<R> apply(Single<T> t){
  return new SingleFlatMapSignalMaybe<T,R>(t,onSuccessHandler,onErrorHandler);
}
