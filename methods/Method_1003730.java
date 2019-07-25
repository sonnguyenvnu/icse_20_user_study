@Override public void error(Throwable throwable){
  if (fire()) {
    continuation.resume(() -> downstream.error(throwable));
  }
 else {
    DefaultExecution.LOGGER.error("",new OverlappingExecutionException("promise already fulfilled",throwable));
  }
}
