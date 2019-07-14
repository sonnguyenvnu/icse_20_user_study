/** 
 * Clears and fills  {@code mBuckets} with buckets 
 */
private List<Bucket<V>> refillBuckets(){
  List<Bucket<V>> bucketsToTrim=new ArrayList<>(mBuckets.size());
  for (int i=0, len=mBuckets.size(); i < len; ++i) {
    final Bucket<V> oldBucket=mBuckets.valueAt(i);
    final int bucketSize=oldBucket.mItemSize;
    final int maxLength=oldBucket.mMaxLength;
    final int bucketInUseCount=oldBucket.getInUseCount();
    if (oldBucket.getFreeListSize() > 0) {
      bucketsToTrim.add(oldBucket);
    }
    mBuckets.setValueAt(i,new Bucket<V>(getSizeInBytes(bucketSize),maxLength,bucketInUseCount,mPoolParams.fixBucketsReinitialization));
  }
  return bucketsToTrim;
}
