/** 
 * Initialize the list of buckets. Get the bucket sizes (and bucket lengths) from the bucket sizes provider
 */
private synchronized void initBuckets(){
  final SparseIntArray bucketSizes=mPoolParams.bucketSizes;
  if (bucketSizes != null) {
    fillBuckets(bucketSizes);
    mAllowNewBuckets=false;
  }
 else {
    mAllowNewBuckets=true;
  }
}
