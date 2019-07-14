/** 
 * Get the value of the latest (current) bucket in the rolling counter for the given  {@link HystrixRollingNumberEvent} type.<p> The  {@link HystrixRollingNumberEvent} must be a "counter" type <code>HystrixRollingNumberEvent.isCounter() == true</code>.
 * @param type HystrixRollingNumberEvent defining which counter to retrieve value from
 * @return value from latest bucket for given  {@link HystrixRollingNumberEvent} counter type
 */
public long getValueOfLatestBucket(HystrixRollingNumberEvent type){
  Bucket lastBucket=getCurrentBucket();
  if (lastBucket == null)   return 0;
  return lastBucket.get(type);
}
