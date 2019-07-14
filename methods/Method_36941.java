@Override public SingleSource<T> apply(Single<T> upstream){
  return upstream.takeUntil(mObservable.firstOrError());
}
