/** 
 * Generate the next duration
 * @param pollCount        The number of times the condition has been polled (evaluated)
 * @param previousDuration The duration of the previously returned poll interval
 * @return Always return the duration that was supplied to this instance of {@link FixedPollInterval}
 */
public Duration next(int pollCount,Duration previousDuration){
  return duration;
}
