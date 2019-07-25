protected void enhance(Step target){
  if (target instanceof AbstractStep) {
    AbstractStep step=(AbstractStep)target;
    step.setJobRepository(properties.getJobRepository());
    Boolean allowStartIfComplete=properties.allowStartIfComplete;
    if (allowStartIfComplete != null) {
      step.setAllowStartIfComplete(allowStartIfComplete);
    }
    step.setStartLimit(properties.startLimit);
    List<StepExecutionListener> listeners=properties.stepExecutionListeners;
    if (!listeners.isEmpty()) {
      step.setStepExecutionListeners(listeners.toArray(new StepExecutionListener[0]));
    }
  }
  if (target instanceof TaskletStep) {
    TaskletStep step=(TaskletStep)target;
    step.setTransactionManager(properties.transactionManager);
  }
}
