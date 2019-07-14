/** 
 * Initialize the list of buckets. Get the bucket sizes (and bucket lengths) from the bucket sizes provider
 * @param inUseCounts map of current buckets and their in use counts
 */
private synchronized void legacyInitBuckets(SparseIntArray inUseCounts){
  Preconditions.checkNotNull(inUseCounts);
  mBuckets.clear();
  final SparseIntArray bucketSizes=mPoolParams.bucketSizes;
  if (bucketSizes != null) {
    for (int i=0; i < bucketSizes.size(); ++i) {
      final int bucketSize=bucketSizes.keyAt(i);
      final int maxLength=bucketSizes.valueAt(i);
      int bucketInUseCount=inUseCounts.get(bucketSize,0);
      mBuckets.put(bucketSize,new Bucket<V>(getSizeInBytes(bucketSize),maxLength,bucketInUseCount,mPoolParams.fixBucketsReinitialization));
    }
    mAllowNewBuckets=false;
  }
 else {
    mAllowNewBuckets=true;
  }
}
