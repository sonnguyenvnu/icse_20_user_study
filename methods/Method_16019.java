@Bean @ConditionalOnMissingBean(ExecutorService.class) public ExecutorService executorService(){
  if (maxThreadPoolSize == -1) {
    maxThreadPoolSize=Runtime.getRuntime().availableProcessors() * 50;
  }
  return Executors.newFixedThreadPool(maxThreadPoolSize);
}
