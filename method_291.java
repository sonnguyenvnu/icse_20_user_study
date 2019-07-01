/** 
 * Initialize the speculative request execution policy.
 * @param scheduler The scheduler service to issue the speculative request
 * @param requestExecutor The executor is used to issue the actual speculative requests
 * @return ScheduledFuture, in case caller needs to cancel it.
 */
@Override public ScheduledFuture<?> _XXXXX_(final ScheduledExecutorService scheduler,final SpeculativeRequestExecutor requestExecutor){
  return scheduleSpeculativeRead(scheduler,requestExecutor,firstSpeculativeRequestTimeout);
}