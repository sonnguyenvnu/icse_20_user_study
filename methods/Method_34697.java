private void commandCompleted(final AbstractCommand<R> commandToCopyStateInto){
  commandToCopyStateInto.executionResult=originalCommand.executionResult;
}
