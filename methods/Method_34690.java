/** 
 * Number of concurrent requests permitted to  {@link HystrixCommand#run()}. Requests beyond the concurrent limit will be rejected. <p> Applicable only when  {@link #executionIsolationStrategy()} == SEMAPHORE.
 * @return {@code HystrixProperty<Integer>}
 */
public HystrixProperty<Integer> executionIsolationSemaphoreMaxConcurrentRequests(){
  return executionIsolationSemaphoreMaxConcurrentRequests;
}
