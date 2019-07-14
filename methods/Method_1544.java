/** 
 * Gets the freelist for the specified bucket. Create the freelist if there isn't one
 * @param bucketedSize the bucket size
 * @return the freelist for the bucket
 */
@VisibleForTesting synchronized Bucket<V> getBucket(int bucketedSize){
  Bucket<V> bucket=mBuckets.get(bucketedSize);
  if (bucket != null || !mAllowNewBuckets) {
    return bucket;
  }
  if (FLog.isLoggable(FLog.VERBOSE)) {
    FLog.v(TAG,"creating new bucket %s",bucketedSize);
  }
  Bucket<V> newBucket=newBucket(bucketedSize);
  mBuckets.put(bucketedSize,newBucket);
  return newBucket;
}
