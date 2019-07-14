/** 
 * ??????1?????????????????????????
 * @param corePoolSize    ??????
 * @param maximumPoolSize ?????
 * @param queue           ?????
 * @param threadFactory   ?????
 * @param handler         ????
 * @return the thread pool executor
 */
public static ThreadPoolExecutor newCachedThreadPool(int corePoolSize,int maximumPoolSize,BlockingQueue<Runnable> queue,ThreadFactory threadFactory,RejectedExecutionHandler handler){
  return new ThreadPoolExecutor(corePoolSize,maximumPoolSize,DateUtils.MILLISECONDS_PER_MINUTE,TimeUnit.MILLISECONDS,queue,threadFactory,handler);
}
