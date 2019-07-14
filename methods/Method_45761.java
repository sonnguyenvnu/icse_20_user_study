/** 
 * ?????????????
 * @param corePoolSize ??????
 * @param queue        ?????
 * @return the thread pool executor
 */
public static ThreadPoolExecutor newFixedThreadPool(int corePoolSize,BlockingQueue<Runnable> queue){
  return new ThreadPoolExecutor(corePoolSize,corePoolSize,0,TimeUnit.MILLISECONDS,queue);
}
