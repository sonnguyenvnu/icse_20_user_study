/** 
 * Get the default threading pool to be used for this HTTP client.
 * @return The default threading pool to be used
 */
protected ExecutorService getDefaultThreadPool(){
  return Executors.newCachedThreadPool();
}
