/** 
 * Get thread count which is running
 * @return thread count which is running
 * @since 0.4.1
 */
public int getThreadAlive(){
  if (threadPool == null) {
    return 0;
  }
  return threadPool.getThreadAlive();
}
