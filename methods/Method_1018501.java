@Override @JsMethod public CompletableFuture<T> exceptionally(Function<Throwable,? extends T> handler){
  if (isDone()) {
    if (isCompletedExceptionally()) {
      try {
        return CompletableFuture.completedFuture(handler.apply(reason));
      }
 catch (      Throwable t) {
        CompletableFuture<T> fut=new CompletableFuture<>();
        fut.completeExceptionally(t);
        return fut;
      }
    }
 else {
      return this;
    }
  }
 else {
    CompletableFuture<T> fut=new CompletableFuture<>();
    errorFutures.add(fut);
    errors.add(handler);
    return fut;
  }
}
