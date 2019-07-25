@Override public Optional<T> apply(Action<T> action){
  checkNotNull(action);
  return Optional.absent();
}
