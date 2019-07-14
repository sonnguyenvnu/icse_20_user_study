/** 
 * Configures the  {@code executor} to use for performing asynchronous executions and listener callbacks. Forexecutions that require a delay, an internal ScheduledExecutorService will be used for the delay, then the  {@code executor} will be used for actual execution.
 * @throws NullPointerException if {@code executor} is null
 */
public FailsafeExecutor<R> with(ExecutorService executor){
  this.scheduler=Scheduler.of(executor);
  return this;
}
