/** 
 * Gets rid of all free values in the pool At the end of this method, mFreeSpace will be zero (reflecting that there are no more free values in the pool). mUsedSpace will however not be reset, since that's a reflection of the values that were allocated via the pool, but are in use elsewhere
 */
@VisibleForTesting void trimToNothing(){
  final List<Bucket<V>> bucketsToTrim;
synchronized (this) {
    if (mPoolParams.fixBucketsReinitialization) {
      bucketsToTrim=refillBuckets();
    }
 else {
      bucketsToTrim=new ArrayList<>(mBuckets.size());
      final SparseIntArray inUseCounts=new SparseIntArray();
      for (int i=0; i < mBuckets.size(); ++i) {
        final Bucket<V> bucket=mBuckets.valueAt(i);
        if (bucket.getFreeListSize() > 0) {
          bucketsToTrim.add(bucket);
        }
        inUseCounts.put(mBuckets.keyAt(i),bucket.getInUseCount());
      }
      legacyInitBuckets(inUseCounts);
    }
    mFree.reset();
    logStats();
  }
  onParamsChanged();
  for (int i=0; i < bucketsToTrim.size(); ++i) {
    final Bucket<V> bucket=bucketsToTrim.get(i);
    while (true) {
      V item=bucket.pop();
      if (item == null) {
        break;
      }
      free(item);
    }
  }
}
