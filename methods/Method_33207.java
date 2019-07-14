private ScheduledExecutorService createThreadPool(){
  return Executors.newScheduledThreadPool(1,runnable -> {
    Thread thread=new Thread(runnable);
    thread.setName("JFXTreeTableView Filter Thread");
    thread.setDaemon(true);
    Runtime.getRuntime().addShutdownHook(thread);
    return thread;
  }
);
}
