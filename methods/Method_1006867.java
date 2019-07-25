/** 
 * @see Flow#resume(String,FlowExecutor)
 */
@Override public FlowExecution resume(String stateName,FlowExecutor executor) throws FlowExecutionException {
  FlowExecutionStatus status=FlowExecutionStatus.UNKNOWN;
  State state=stateMap.get(stateName);
  if (logger.isDebugEnabled()) {
    logger.debug("Resuming state=" + stateName + " with status=" + status);
  }
  StepExecution stepExecution=null;
  while (isFlowContinued(state,status,stepExecution)) {
    stateName=state.getName();
    try {
      if (logger.isDebugEnabled()) {
        logger.debug("Handling state=" + stateName);
      }
      status=state.handle(executor);
      stepExecution=executor.getStepExecution();
    }
 catch (    FlowExecutionException e) {
      executor.close(new FlowExecution(stateName,status));
      throw e;
    }
catch (    Exception e) {
      executor.close(new FlowExecution(stateName,status));
      throw new FlowExecutionException(String.format("Ended flow=%s at state=%s with exception",name,stateName),e);
    }
    if (logger.isDebugEnabled()) {
      logger.debug("Completed state=" + stateName + " with status=" + status);
    }
    state=nextState(stateName,status,stepExecution);
  }
  FlowExecution result=new FlowExecution(stateName,status);
  executor.close(result);
  return result;
}
