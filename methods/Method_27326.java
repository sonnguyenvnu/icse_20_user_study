@SafeVarargs static <T>Optional<T> returnNonNull(@NonNull T... t){
  return Stream.of(t).filter(ObjectsCompat::nonNull).findFirst();
}
