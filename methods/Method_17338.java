/** 
 * Records cache evictions.
 * @param count the number of evictions to record
 */
public void recordEvictions(@NonNegative long count){
  if (enabled) {
    evictions.add(count);
  }
}
