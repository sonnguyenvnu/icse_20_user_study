@Override public void success(T value){
  if (fire()) {
    continuation.resume(() -> downstream.success(value));
  }
 else {
    DefaultExecution.LOGGER.error("",new OverlappingExecutionException("promise already fulfilled"));
  }
}
