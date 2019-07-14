/** 
 * ??????1?????????????????????????
 * @param corePoolSize    ??????
 * @param maximumPoolSize ?????
 * @param keepAliveTime   ????
 * @param queue           ?????
 * @param threadFactory   ?????
 * @param handler         ????
 * @return the thread pool executor
 */
public static ThreadPoolExecutor newCachedThreadPool(int corePoolSize,int maximumPoolSize,int keepAliveTime,BlockingQueue<Runnable> queue,ThreadFactory threadFactory,RejectedExecutionHandler handler){
  return new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,TimeUnit.MILLISECONDS,queue,threadFactory,handler);
}
