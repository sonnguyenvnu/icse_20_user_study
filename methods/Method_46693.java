@Bean(destroyMethod="shutdown") public ExecutorService executorService(){
  int coreSize=Runtime.getRuntime().availableProcessors() * 2;
  return new ThreadPoolExecutor(coreSize,coreSize,0L,TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>()){
    @Override public void shutdown(){
      super.shutdown();
      try {
        this.awaitTermination(10,TimeUnit.MINUTES);
      }
 catch (      InterruptedException ignored) {
      }
    }
  }
;
}
