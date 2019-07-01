public static CompletableFuture<Void> _XXXXX_(@Nullable AsyncCloseable closeable,boolean swallowIOException){
  if (null == closeable) {
    return FutureUtils.Void();
  }
 else   if (swallowIOException) {
    return FutureUtils.ignore(closeable.asyncClose());
  }
 else {
    return closeable.asyncClose();
  }
}