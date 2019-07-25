@Override public void open(ExecutionContext executionContext) throws ItemStreamException {
  try {
    String executionContextKey=getExecutionContextKey(checkpointKey);
    Serializable checkpoint=(Serializable)executionContext.get(executionContextKey);
    doOpen(checkpoint);
  }
 catch (  Exception e) {
    throw new ItemStreamException(e);
  }
}
