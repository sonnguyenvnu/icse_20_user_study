/** 
 * Starts the variable sampler, which will fetch variables  {@link Stats} on the given period.
 */
@Override public void start(ShutdownRegistry shutdownRegistry){
  checkNotNull(shutdownRegistry);
  checkNotNull(samplePeriod);
  Preconditions.checkArgument(samplePeriod.getValue() > 0);
  final ScheduledExecutorService executor=new ScheduledThreadPoolExecutor(1,new ThreadFactoryBuilder().setNameFormat("VariableSampler-%d").setDaemon(true).build());
  final AtomicBoolean shouldSample=new AtomicBoolean(true);
  final Runnable sampler=new Runnable(){
    @Override public void run(){
      if (shouldSample.get()) {
        try {
          runSampler(Clock.SYSTEM_CLOCK);
        }
 catch (        Exception e) {
          LOG.log(Level.SEVERE,"ignoring runSampler failure",e);
        }
      }
    }
  }
;
  executor.scheduleAtFixedRate(sampler,samplePeriod.getValue(),samplePeriod.getValue(),samplePeriod.getUnit().getTimeUnit());
  shutdownRegistry.addAction(new Command(){
    @Override public void execute() throws RuntimeException {
      shouldSample.set(false);
      executor.shutdown();
      LOG.info("Variable sampler shut down");
    }
  }
);
}
