@Deactivate void deactivate(){
  while (!scheduledJobs.isEmpty()) {
    final ScheduledCompletableFuture<?> scheduledJob;
synchronized (scheduledJobs) {
      if (scheduledJobs.isEmpty()) {
        return;
      }
      Iterator<ScheduledCompletableFuture<?>> iterator=scheduledJobs.iterator();
      scheduledJob=iterator.next();
      iterator.remove();
    }
    scheduledJob.cancel(true);
  }
}
