@Override public Single<R> apply(Single<T> t){
  return new SingleFlatMapSignalSingle<T,R>(t,onSuccessHandler,onErrorHandler);
}
