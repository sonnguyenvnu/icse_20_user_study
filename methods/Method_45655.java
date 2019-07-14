public static ThreadPoolExecutor initPool(ServerConfig serverConfig){
  int minPoolSize=serverConfig.getCoreThreads();
  int maxPoolSize=serverConfig.getMaxThreads();
  int queueSize=serverConfig.getQueues();
  int aliveTime=serverConfig.getAliveTime();
  BlockingQueue<Runnable> poolQueue=queueSize > 0 ? new LinkedBlockingQueue<Runnable>(queueSize) : new SynchronousQueue<Runnable>();
  return new ThreadPoolExecutor(minPoolSize,maxPoolSize,aliveTime,TimeUnit.MILLISECONDS,poolQueue);
}
