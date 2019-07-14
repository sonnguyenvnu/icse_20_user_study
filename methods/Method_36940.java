@Override public ObservableSource<T> apply(Observable<T> upstream){
  return upstream.takeUntil(mObservable);
}
