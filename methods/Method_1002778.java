@Override public void accept(Void aVoid,Throwable thrown){
  if (thrown != null && !(thrown instanceof CancelledSubscriptionException) && !(thrown instanceof AbortedStreamException)) {
    future.completeExceptionally(thrown);
  }
 else {
    future.complete(firstNonNull(headers,HttpHeaders.of()));
  }
}
