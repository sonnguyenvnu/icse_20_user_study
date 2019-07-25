@Override public Completable apply(Single<T> t){
  return new SingleFlatMapSignalCompletable<T>(t,onSuccessHandler,onErrorHandler);
}
