@Override public @NonNull Observable<T> call(final @NonNull Observable<T> source){
  return source.map(coalesceWith(this.theDefault));
}
