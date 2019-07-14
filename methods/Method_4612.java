/** 
 * Returns the time in microseconds for a given table index.
 * @param tableIndex A table index in the range [0, 100].
 * @return The corresponding time in microseconds.
 */
private long getTimeUsForTableIndex(int tableIndex){
  return (durationUs * tableIndex) / 100;
}
