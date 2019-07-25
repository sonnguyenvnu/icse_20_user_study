@Override public Observable<R> apply(Single<T> t){
  return new SingleFlatMapSignalObservable<T,R>(t,onSuccessHandler,onErrorHandler);
}
