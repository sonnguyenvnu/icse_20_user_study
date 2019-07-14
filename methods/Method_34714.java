/** 
 * Invoked each time a thread is executed.
 */
public void markThreadExecution(){
  concurrentExecutionCount.incrementAndGet();
}
