/** 
 * Returns true if the pool size (sum of the used and the free portions) exceeds its 'max size' soft cap as specified by the pool parameters.
 */
@VisibleForTesting synchronized boolean isMaxSizeSoftCapExceeded(){
  final boolean isMaxSizeSoftCapExceeded=(mUsed.mNumBytes + mFree.mNumBytes) > mPoolParams.maxSizeSoftCap;
  if (isMaxSizeSoftCapExceeded) {
    mPoolStatsTracker.onSoftCapReached();
  }
  return isMaxSizeSoftCapExceeded;
}
