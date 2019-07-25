@Override public Collection<StepExecution> handle(StepExecutionSplitter stepSplitter,StepExecution stepExecution) throws Exception {
  final List<Future<StepExecution>> tasks=new ArrayList<>();
  final Set<StepExecution> result=new HashSet<>();
  final ThreadPoolTaskExecutor taskExecutor=new ThreadPoolTaskExecutor();
  int stepExecutionCount=jobRepository.getStepExecutionCount(stepExecution.getJobExecution().getJobInstance(),stepExecution.getStepName());
  boolean isRestart=stepExecutionCount > 1;
  Set<StepExecution> partitionStepExecutions=splitStepExecution(stepExecution,isRestart);
  for (  StepExecution curStepExecution : partitionStepExecutions) {
    partitionStepNames.add(curStepExecution.getStepName());
  }
  taskExecutor.setCorePoolSize(threads);
  taskExecutor.setMaxPoolSize(threads);
  taskExecutor.initialize();
  try {
    for (    final StepExecution curStepExecution : partitionStepExecutions) {
      final FutureTask<StepExecution> task=createTask(step,curStepExecution);
      try {
        taskExecutor.execute(task);
        tasks.add(task);
      }
 catch (      TaskRejectedException e) {
        ExitStatus exitStatus=ExitStatus.FAILED.addExitDescription("TaskExecutor rejected the task for this step.");
        curStepExecution.setStatus(BatchStatus.FAILED);
        curStepExecution.setExitStatus(exitStatus);
        result.add(stepExecution);
      }
    }
    processPartitionResults(tasks,result);
  }
  finally {
    taskExecutor.shutdown();
  }
  return result;
}
