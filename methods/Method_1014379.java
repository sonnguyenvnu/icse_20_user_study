private void timeout(){
  if (!future.isDone()) {
    future.completeExceptionally(new TimeoutException());
  }
}
