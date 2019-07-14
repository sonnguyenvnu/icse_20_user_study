@Override protected boolean isFailure(ExecutionResult result){
  long elapsedNanos=execution.getElapsedTime().toNanos();
  Duration timeout=policy.getTimeout();
  boolean timeoutExceeded=timeout != null && elapsedNanos >= timeout.toNanos();
  return timeoutExceeded || super.isFailure(result);
}
