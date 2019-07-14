/** 
 * Return the number of unique  {@link HystrixThreadPool}s that have been registered
 * @return number of unique {@link HystrixThreadPool}s that have been registered
 */
public static int getThreadPoolCount(){
  return HystrixThreadPoolKey.Factory.getThreadPoolCount();
}
