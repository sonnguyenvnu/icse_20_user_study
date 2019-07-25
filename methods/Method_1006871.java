@Override public FlowExecutionStatus handle(FlowExecutor executor) throws Exception {
  FlowExecutionStatus result=super.handle(executor);
  executor.getJobExecution().getExecutionContext().put("batch.lastSteps",Collections.singletonList(getStep().getName()));
  return result;
}
