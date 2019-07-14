/** 
 * Returns number of created result sets that are still not explicitly closed.
 * @see #getTotalOpenResultSetCount()
 */
public int getOpenResultSetCount(){
  return resultSets == null ? 0 : resultSets.size();
}
