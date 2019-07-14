private void commandUnsubscribed(final AbstractCommand<R> commandToCopyStateInto){
  commandToCopyStateInto.executionResult=commandToCopyStateInto.executionResult.addEvent(HystrixEventType.CANCELLED);
  commandToCopyStateInto.executionResult=commandToCopyStateInto.executionResult.setExecutionLatency(-1);
}
