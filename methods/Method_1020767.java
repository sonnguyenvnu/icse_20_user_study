@Override public Flowable<R> apply(Single<T> t){
  return new SingleFlatMapSignalFlowable<T,R>(t,onSuccessHandler,onErrorHandler);
}
