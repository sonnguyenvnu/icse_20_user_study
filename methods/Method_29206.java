@Override public double getUsedPercentage(){
  if (threadPoolExecutor == null) {
    return 0;
  }
  long completedTaskCount=threadPoolExecutor.getCompletedTaskCount();
  long taskCount=threadPoolExecutor.getTaskCount();
  long runningCount=(taskCount - completedTaskCount);
  if (runningCount < 0L) {
    runningCount=0L;
  }
  long maxPoolSize=threadPoolExecutor.getMaximumPoolSize();
  double run=Double.valueOf(runningCount);
  double size=Double.valueOf(maxPoolSize);
  if (run == 0d || size == 0d) {
    return 0D;
  }
  return run / size;
}
