/** 
 * Return the  {@link FlowExecutionStatus} stored.
 * @see State#handle(FlowExecutor)
 */
@Override public FlowExecutionStatus handle(FlowExecutor executor) throws Exception {
synchronized (executor) {
    StepExecution stepExecution=executor.getStepExecution();
    if (stepExecution != null && executor.getStepExecution().getStatus() == BatchStatus.UNKNOWN) {
      return FlowExecutionStatus.UNKNOWN;
    }
    if (status.isStop()) {
      if (!executor.isRestart()) {
        if (abandon) {
          executor.abandonStepExecution();
        }
      }
 else {
        return FlowExecutionStatus.COMPLETED;
      }
    }
    setExitStatus(executor,code);
    return status;
  }
}
