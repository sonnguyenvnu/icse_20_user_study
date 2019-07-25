@Override public ObservableSource<Long> apply(Observable<T> upstream){
  return new ObservableIndexOf<T>(upstream,predicate);
}
