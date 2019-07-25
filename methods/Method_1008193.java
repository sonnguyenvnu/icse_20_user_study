public static Builder builder(BiConsumer<BulkRequest,ActionListener<BulkResponse>> consumer,Listener listener){
  Objects.requireNonNull(consumer,"consumer");
  Objects.requireNonNull(listener,"listener");
  final ScheduledThreadPoolExecutor scheduledThreadPoolExecutor=Scheduler.initScheduler(Settings.EMPTY);
  return new Builder(consumer,listener,(delay,executor,command) -> scheduledThreadPoolExecutor.schedule(command,delay.millis(),TimeUnit.MILLISECONDS),() -> Scheduler.terminate(scheduledThreadPoolExecutor,10,TimeUnit.SECONDS));
}
