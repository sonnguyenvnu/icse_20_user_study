public static <T>Iterable<T> asIterable(final T handler,final T handler2,final T[] handlers){
  checkNotNull(handler);
  checkNotNull(handler2);
  checkNotNull(handlers);
  if (handlers.length == 0) {
    return of(handler,handler2);
  }
  return ImmutableList.<T>builder().add(handler).add(handler2).add(handlers).build();
}
