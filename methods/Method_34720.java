/** 
 * Invoked each time a command is rejected from the thread-pool
 */
public void markThreadRejection(){
  concurrentExecutionCount.decrementAndGet();
}
