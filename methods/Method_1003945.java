/** 
 * WARNING: You need to call refresh() to recompute the variance
 * @return the range of the aggregated windows
 */
public long range(){
  return max - min;
}
