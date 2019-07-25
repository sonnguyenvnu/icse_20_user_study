public static void initialise(final int jobExecutorThreadCount){
  LOGGER.debug("Initialising ExecutorService with " + jobExecutorThreadCount + " threads");
  service=Executors.newScheduledThreadPool(jobExecutorThreadCount,runnable -> {
    final Thread thread=new Thread(runnable);
    thread.setDaemon(true);
    return thread;
  }
);
}
