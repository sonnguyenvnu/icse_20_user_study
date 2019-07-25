public Optional<T> filter(Predicate<? super T> predicate){
  checkNotNull(predicate);
  if (!isPresent() || predicate.test(ref)) {
    return this;
  }
  return empty();
}
