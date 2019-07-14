/** 
 * Can we allocate a value of size 'sizeInBytes' without exceeding the hard cap on the pool size? If allocating this value will take the pool over the hard cap, we will first trim the pool down to its soft cap, and then check again. If the current used bytes + this new value will take us above the hard cap, then we return false immediately - there is no point freeing up anything.
 * @param sizeInBytes the size (in bytes) of the value to allocate
 * @return true, if we can allocate this; false otherwise
 */
@VisibleForTesting synchronized boolean canAllocate(int sizeInBytes){
  int hardCap=mPoolParams.maxSizeHardCap;
  if (sizeInBytes > hardCap - mUsed.mNumBytes) {
    mPoolStatsTracker.onHardCapReached();
    return false;
  }
  int softCap=mPoolParams.maxSizeSoftCap;
  if (sizeInBytes > softCap - (mUsed.mNumBytes + mFree.mNumBytes)) {
    trimToSize(softCap - sizeInBytes);
  }
  if (sizeInBytes > hardCap - (mUsed.mNumBytes + mFree.mNumBytes)) {
    mPoolStatsTracker.onHardCapReached();
    return false;
  }
  return true;
}
