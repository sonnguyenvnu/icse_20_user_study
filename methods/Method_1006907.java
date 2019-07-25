/** 
 * Convenience method for subclasses to determine if the step is concurrent.
 * @return true if the tasklet is going to be run in multiple threads
 */
protected boolean concurrent(){
  boolean concurrent=taskExecutor != null && !(taskExecutor instanceof SyncTaskExecutor);
  return concurrent;
}
