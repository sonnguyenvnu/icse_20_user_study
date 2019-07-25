@Override public Void apply(Void unused,Throwable cause){
  if (future.isDone()) {
    return null;
  }
  if (cause != null && !(cause instanceof CancelledSubscriptionException) && !(cause instanceof AbortedStreamException)) {
    future.completeExceptionally(cause);
  }
 else {
    future.complete(ResponseHeaders.of(HttpStatus.UNKNOWN));
  }
  return null;
}
