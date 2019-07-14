/** 
 * Records cache hits. This should be called when a cache request returns a cached value.
 * @param count the number of hits to record
 */
public void recordHits(@NonNegative long count){
  if (enabled) {
    hits.add(count);
  }
}
