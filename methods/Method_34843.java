/** 
 * Get the cumulative sum of all buckets ever since the JVM started without rolling for the given  {@link HystrixRollingNumberEvent} type.<p> See  {@link #getRollingSum(HystrixRollingNumberEvent)} for the rolling sum.<p> The  {@link HystrixRollingNumberEvent} must be a "counter" type <code>HystrixRollingNumberEvent.isCounter() == true</code>.
 * @param type HystrixRollingNumberEvent defining which counter to retrieve values from
 * @return cumulative sum of all increments and adds for the given {@link HystrixRollingNumberEvent} counter type
 */
public long getCumulativeSum(HystrixRollingNumberEvent type){
  return getValueOfLatestBucket(type) + cumulativeSum.get(type);
}
