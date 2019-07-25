@Override public ObservableSource<T> apply(Observable<T> upstream){
  return new ObservableObserveOnDrop<T>(upstream,scheduler);
}
