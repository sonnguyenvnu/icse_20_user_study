/** 
 * This function is called when a key is processed that is not currently assigned a counter, and all the counters are in use. This function estimates the median of the counters in the sketch via sampling, decrements all counts by this estimate, throws out all counters that are no longer positive, and increments offset accordingly.
 * @param sampleSize number of samples
 * @return the median value
 */
long purge(final int sampleSize){
  final int limit=Math.min(sampleSize,getNumActive());
  int numSamples=0;
  int i=0;
  final long[] samples=new long[limit];
  while (numSamples < limit) {
    if (isActive(i)) {
      samples[numSamples]=values[i];
      numSamples++;
    }
    i++;
  }
  final long val=QuickSelect.select(samples,0,numSamples - 1,limit / 2);
  adjustAllValuesBy(-1 * val);
  keepOnlyPositiveCounts();
  return val;
}
