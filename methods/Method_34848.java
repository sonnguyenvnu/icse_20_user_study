/** 
 * This returns the mean (average) of all values in the current snapshot. This is not a percentile but often desired so captured and exposed here.
 * @return mean of all values
 */
public int getMean(){
  if (!enabled.get())   return -1;
  getCurrentBucket();
  return getCurrentPercentileSnapshot().getMean();
}
