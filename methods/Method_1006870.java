/** 
 * Aggregate all of the  {@link FlowExecutionStatus}es of the {@link FlowExecution}s into one status. The aggregate status will be the status with the highest precedence.
 * @see FlowExecutionAggregator#aggregate(Collection)
 */
@Override public FlowExecutionStatus aggregate(Collection<FlowExecution> executions){
  if (executions == null || executions.size() == 0) {
    return FlowExecutionStatus.UNKNOWN;
  }
  return Collections.max(executions).getStatus();
}
