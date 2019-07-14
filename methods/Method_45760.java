/** 
 * ???????????
 * @param corePoolSize ??????
 * @return the thread pool executor
 */
public static ThreadPoolExecutor newFixedThreadPool(int corePoolSize){
  return new ThreadPoolExecutor(corePoolSize,corePoolSize,0,TimeUnit.MILLISECONDS,new SynchronousQueue<Runnable>());
}
