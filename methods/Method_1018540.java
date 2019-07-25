@Override public synchronized CompletableFuture<AsyncReader> reset(){
  try {
    raf.seek(0);
    return CompletableFuture.completedFuture(this);
  }
 catch (  IOException e) {
    CompletableFuture<AsyncReader> err=new CompletableFuture<>();
    err.completeExceptionally(e);
    return err;
  }
}
