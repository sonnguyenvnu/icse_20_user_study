/** 
 * Configures the  {@code executor} to use for performing asynchronous executions and listener callbacks.
 * @throws NullPointerException if {@code executor} is null
 */
public FailsafeExecutor<R> with(ScheduledExecutorService executor){
  this.scheduler=Scheduler.of(executor);
  return this;
}
