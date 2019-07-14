/** 
 * Get an array of values for all buckets in the rolling counter for the given  {@link HystrixRollingNumberEvent} type.<p> Index 0 is the oldest bucket. <p> The  {@link HystrixRollingNumberEvent} must be a "counter" type <code>HystrixRollingNumberEvent.isCounter() == true</code>.
 * @param type HystrixRollingNumberEvent defining which counter to retrieve values from
 * @return array of values from each of the rolling buckets for given {@link HystrixRollingNumberEvent} counter type
 */
public long[] getValues(HystrixRollingNumberEvent type){
  Bucket lastBucket=getCurrentBucket();
  if (lastBucket == null)   return new long[0];
  Bucket[] bucketArray=buckets.getArray();
  long values[]=new long[bucketArray.length];
  int i=0;
  for (  Bucket bucket : bucketArray) {
    if (type.isCounter()) {
      values[i++]=bucket.getAdder(type).sum();
    }
 else     if (type.isMaxUpdater()) {
      values[i++]=bucket.getMaxUpdater(type).max();
    }
  }
  return values;
}
