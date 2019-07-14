@Override public @NonNull Observable<T> call(final @NonNull Observable<Notification<T>> source){
  return source.filter(Notification::isOnNext).map(Notification::getValue);
}
