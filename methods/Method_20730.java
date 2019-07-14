@Override public Observable<T> call(final @NonNull Observable<T> source){
  return this.until.take(1).flatMap(__ -> source);
}
