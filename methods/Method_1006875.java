/** 
 * Returns the same number of  {@link StepExecution}s as the gridSize specifies.  Each of the child StepExecutions will <em>not</em> be available via the  {@link JsrJobOperator} perJSR-352.
 * @see <a href="https://java.net/projects/jbatch/lists/public/archive/2013-10/message/10">https://java.net/projects/jbatch/lists/public/archive/2013-10/message/10</a>
 */
@Override public Set<StepExecution> split(StepExecution stepExecution,int gridSize) throws JobExecutionException {
  Set<StepExecution> executions=new TreeSet<>(new Comparator<StepExecution>(){
    @Override public int compare(    StepExecution arg0,    StepExecution arg1){
      String r1="";
      String r2="";
      if (arg0 != null) {
        r1=arg0.getStepName();
      }
      if (arg1 != null) {
        r2=arg1.getStepName();
      }
      return r1.compareTo(r2);
    }
  }
);
  JobExecution jobExecution=stepExecution.getJobExecution();
  for (int i=0; i < gridSize; i++) {
    String stepName=this.stepName + ":partition" + i;
    JobExecution curJobExecution=new JobExecution(jobExecution);
    StepExecution curStepExecution=new StepExecution(stepName,curJobExecution);
    if (!restoreState || isStartable(curStepExecution,new ExecutionContext())) {
      executions.add(curStepExecution);
    }
  }
  jobRepository.addAll(executions);
  return executions;
}
