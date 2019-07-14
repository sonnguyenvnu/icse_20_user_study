/** 
 * Returns the number of times  {@link Cache} lookup methods have returned either a cached oruncached value. This is defined as  {@code hitCount + missCount}. <p> <b>Note:</b> the values of the metrics are undefined in case of overflow (though it is guaranteed not to throw an exception). If you require specific handling, we recommend implementing your own stats collector.
 * @return the {@code hitCount + missCount}
 */
@NonNegative public long requestCount(){
  return saturatedAdd(hitCount,missCount);
}
