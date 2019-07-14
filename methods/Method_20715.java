@Override public @NonNull Observable<Void> call(final @NonNull Observable<Notification<T>> source){
  return source.filter(Notification::isOnCompleted).map(__ -> null);
}
