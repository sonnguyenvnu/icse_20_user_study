/** 
 * Trim the (free portion of the) pool so that the pool size is at or below the soft cap. This will try to free up values in the free portion of the pool, until (a) the pool size is now below the soft cap configured OR (b) the free portion of the pool is empty
 */
@VisibleForTesting synchronized void trimToSoftCap(){
  if (isMaxSizeSoftCapExceeded()) {
    trimToSize(mPoolParams.maxSizeSoftCap);
  }
}
