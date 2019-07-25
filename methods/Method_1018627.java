public static <T>CompletableFuture<T> errored(Throwable t){
  CompletableFuture<T> err=new CompletableFuture<>();
  err.completeExceptionally(t);
  return err;
}
