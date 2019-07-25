/** 
 * Square root of variance.
 * @param unbiased Normalizes variance by N-1 when true, and by N otherwise.
 * @see variance
 */
public static SparseVector stddev(InstanceList instances,boolean unbiased){
  return stddev(instances,mean(instances),unbiased);
}
