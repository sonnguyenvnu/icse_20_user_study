/** 
 * Invoked each time a thread completes.
 */
public void markThreadCompletion(){
  concurrentExecutionCount.decrementAndGet();
}
