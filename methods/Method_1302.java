/** 
 * Factory method to create a new  {@code ConstrainedExecutorService} with an unbounded{@link LinkedBlockingQueue} queue.
 * @param name Friendly name to identify the executor in logging and reporting.
 * @param maxConcurrency Maximum number of tasks to execute in parallel on the delegate executor.
 * @param queueSize Number of items that can be queued before new submissions are rejected.
 * @param executor Delegate executor for actually running tasks.
 * @return new {@code ConstrainedExecutorService} instance.
 */
public static ConstrainedExecutorService newConstrainedExecutor(String name,int maxConcurrency,int queueSize,Executor executor){
  return new ConstrainedExecutorService(name,maxConcurrency,executor,new LinkedBlockingQueue<Runnable>(queueSize));
}
