/** 
 * Records cache misses. This should be called when a cache request returns a value that was not found in the cache.
 * @param count the number of misses to record
 */
public void recordMisses(@NonNegative long count){
  if (enabled) {
    misses.add(count);
  }
}
