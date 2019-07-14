/** 
 * ??????1????????????
 * @param corePoolSize    ??????
 * @param maximumPoolSize ?????
 * @return the thread pool executor
 */
public static ThreadPoolExecutor newCachedThreadPool(int corePoolSize,int maximumPoolSize){
  return new ThreadPoolExecutor(corePoolSize,maximumPoolSize,DateUtils.MILLISECONDS_PER_MINUTE,TimeUnit.MILLISECONDS,new SynchronousQueue<Runnable>());
}
