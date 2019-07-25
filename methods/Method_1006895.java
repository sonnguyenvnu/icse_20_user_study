/** 
 * @see StepExecutionSplitter#split(StepExecution,int)
 */
@Override public Set<StepExecution> split(StepExecution stepExecution,int gridSize) throws JobExecutionException {
  JobExecution jobExecution=stepExecution.getJobExecution();
  Map<String,ExecutionContext> contexts=getContexts(stepExecution,gridSize);
  Set<StepExecution> set=new HashSet<>(contexts.size());
  for (  Entry<String,ExecutionContext> context : contexts.entrySet()) {
    String stepName=this.stepName + STEP_NAME_SEPARATOR + context.getKey();
    StepExecution currentStepExecution=jobExecution.createStepExecution(stepName);
    boolean startable=isStartable(currentStepExecution,context.getValue());
    if (startable) {
      set.add(currentStepExecution);
    }
  }
  jobRepository.addAll(set);
  Set<StepExecution> executions=new HashSet<>(set.size());
  executions.addAll(set);
  return executions;
}
