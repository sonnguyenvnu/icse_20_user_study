public static <T>Iterable<T> asIterable(final T handler,final T[] handlers){
  checkNotNull(handler);
  checkNotNull(handlers);
  if (handlers.length == 0) {
    return of(handler);
  }
  return ImmutableList.<T>builder().add(handler).add(handlers).build();
}
