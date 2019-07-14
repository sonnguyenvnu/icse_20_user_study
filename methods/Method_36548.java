/** 
 * Create thread pool to execute async init task
 * @return
 */
private static ThreadPoolExecutor createThreadPoolExecutor(Environment environment){
  int threadPoolCoreSize=CPU_COUNT + 1;
  String coreSizeStr=environment.getProperty(SofaBootInfraConstants.ASYNC_INIT_BEAN_CORE_SIZE);
  if (coreSizeStr != null) {
    threadPoolCoreSize=Integer.parseInt(coreSizeStr);
  }
  int threadPoolMaxSize=CPU_COUNT + 1;
  String maxSizeStr=environment.getProperty(SofaBootInfraConstants.ASYNC_INIT_BEAN_MAX_SIZE);
  if (maxSizeStr != null) {
    threadPoolMaxSize=Integer.parseInt(maxSizeStr);
  }
  SofaLogger.info(String.format("create async-init-bean thread pool, corePoolSize: %d, maxPoolSize: %d.",threadPoolCoreSize,threadPoolMaxSize));
  return new ThreadPoolExecutor(threadPoolCoreSize,threadPoolMaxSize,30,TimeUnit.SECONDS,new SynchronousQueue<Runnable>(),new NamedThreadFactory("async-init-bean"),new ThreadPoolExecutor.CallerRunsPolicy());
}
