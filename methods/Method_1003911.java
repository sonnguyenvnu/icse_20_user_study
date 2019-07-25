/** 
 * Initiates the poller. The poller may only be started once.  If an attempt is made to start a poller when it is already started,  {@link IllegalStateException} will be thrown.
 * @param pollInterval Fixed poll rate.
 */
public void start(Amount<Long,Time> pollInterval){
  Preconditions.checkNotNull(pollInterval);
  Preconditions.checkArgument(pollInterval.getValue() > 0,"Poll interval must be positive");
  Preconditions.checkState(started.compareAndSet(false,true),"Poller is already started.");
  long pollIntervalMs=pollInterval.as(Time.MILLISECONDS);
  executor.scheduleAtFixedRate(sampler,pollIntervalMs,pollIntervalMs,TimeUnit.MILLISECONDS);
}
