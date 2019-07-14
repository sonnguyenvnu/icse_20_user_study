private void addJob(Object jobId,StandardScannerExecutor executor){
  for (  Map.Entry<Object,StandardScannerExecutor> jobs : runningJobs.entrySet()) {
    StandardScannerExecutor exe=jobs.getValue();
    if (exe.isDone() || exe.isCancelled()) {
      runningJobs.remove(jobs.getKey(),exe);
    }
  }
  Preconditions.checkArgument(runningJobs.putIfAbsent(jobId,executor) == null,"Another job with the same id is already running: %s",jobId);
}
