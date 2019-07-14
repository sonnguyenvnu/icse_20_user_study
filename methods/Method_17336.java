/** 
 * Records cache removals.
 * @param count the number of removals to record
 */
public void recordRemovals(@NonNegative long count){
  if (enabled) {
    removals.add(count);
  }
}
