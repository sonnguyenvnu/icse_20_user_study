/** 
 * Clears and fills  {@code mBuckets} with buckets
 * @param bucketSizes bucket size to bucket's max length
 */
private void fillBuckets(SparseIntArray bucketSizes){
  mBuckets.clear();
  for (int i=0; i < bucketSizes.size(); ++i) {
    final int bucketSize=bucketSizes.keyAt(i);
    final int maxLength=bucketSizes.valueAt(i);
    mBuckets.put(bucketSize,new Bucket<V>(getSizeInBytes(bucketSize),maxLength,0,mPoolParams.fixBucketsReinitialization));
  }
}
