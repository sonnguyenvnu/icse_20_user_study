/** 
 * Gets the max thread count of thread pool.
 * @return max thread count
 */
public static int getMaxThreadCount(){
  return EXECUTOR_SERVICE.getMaximumPoolSize();
}
