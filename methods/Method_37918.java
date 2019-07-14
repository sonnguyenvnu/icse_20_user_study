private static ThreadFactory get(final ThreadFactoryBuilder builder){
  final String nameFormat=builder.nameFormat;
  final Boolean daemon=builder.daemonThread;
  final Integer priority=builder.priority;
  final Thread.UncaughtExceptionHandler uncaughtExceptionHandler=builder.uncaughtExceptionHandler;
  final ThreadFactory backingThreadFactory=(builder.backingThreadFactory != null) ? builder.backingThreadFactory : Executors.defaultThreadFactory();
  final AtomicLong count=(nameFormat != null) ? new AtomicLong(0) : null;
  return runnable -> {
    final Thread thread=backingThreadFactory.newThread(runnable);
    if (nameFormat != null) {
      final String name=String.format(nameFormat,count.getAndIncrement());
      thread.setName(name);
    }
    if (daemon != null) {
      thread.setDaemon(daemon);
    }
    if (priority != null) {
      thread.setPriority(priority);
    }
    if (uncaughtExceptionHandler != null) {
      thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
    }
    return thread;
  }
;
}
