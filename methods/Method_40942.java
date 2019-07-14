private static ScheduledExecutorService delayer(){
  if (delayer == null) {
synchronized (DelegatingScheduler.class) {
      if (delayer == null)       delayer=new ScheduledThreadPoolExecutor(1,new DelayerThreadFactory());
    }
  }
  return delayer;
}
