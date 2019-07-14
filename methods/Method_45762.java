/** 
 * ???????????????????
 * @param corePoolSize  ??????
 * @param queue         ?????
 * @param threadFactory ?????
 * @return the thread pool executor
 */
public static ThreadPoolExecutor newFixedThreadPool(int corePoolSize,BlockingQueue<Runnable> queue,ThreadFactory threadFactory){
  return new ThreadPoolExecutor(corePoolSize,corePoolSize,0,TimeUnit.MILLISECONDS,queue,threadFactory);
}
