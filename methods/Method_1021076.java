public static <T>Try<T> failure(final Exception exception){
  return new Failure<>(exception);
}
