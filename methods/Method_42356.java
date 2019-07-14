public void showExecutorInfo(){
  LOG.info("NotifyExecutor Info : corePoolSize = " + corePoolSize + " | maxPoolSize = " + maxPoolSize + " | workQueueSize = " + workQueueSize + " | taskCount = " + executor.getTaskCount() + " | activeCount = " + executor.getActiveCount() + " | completedTaskCount = " + executor.getCompletedTaskCount());
}
