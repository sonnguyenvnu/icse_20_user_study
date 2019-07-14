public static <T>CompletableFuture<T> within(final CompletableFuture<T> future,final Duration duration){
  final CompletableFuture<T> timeout=failAfter(duration);
  return future.applyToEither(timeout,Function.identity());
}
