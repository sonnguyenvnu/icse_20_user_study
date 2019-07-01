private static <T>CompletableFuture<T> _XXXXX_(){
  return FutureUtils.exception(new BKException.BKClientClosedException());
}