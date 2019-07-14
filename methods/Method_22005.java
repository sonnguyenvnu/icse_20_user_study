@Override public ExecutorService createExecutorService(final String jobName){
  return new ExecutorServiceObject("inner-job-" + jobName,Runtime.getRuntime().availableProcessors() * 2).createExecutorService();
}
