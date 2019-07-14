/** 
 * Helper method that sets the cache size limit to be either a high, or a low limit. If there is not enough free space to satisfy the high limit, it is set to the low limit.
 */
@GuardedBy("mLock") private void updateFileCacheSizeLimit(){
  boolean isAvailableSpaceLowerThanHighLimit;
  StatFsHelper.StorageType storageType=mStorage.isExternal() ? StatFsHelper.StorageType.EXTERNAL : StatFsHelper.StorageType.INTERNAL;
  isAvailableSpaceLowerThanHighLimit=mStatFsHelper.testLowDiskSpace(storageType,mDefaultCacheSizeLimit - mCacheStats.getSize());
  if (isAvailableSpaceLowerThanHighLimit) {
    mCacheSizeLimit=mLowDiskSpaceCacheSizeLimit;
  }
 else {
    mCacheSizeLimit=mDefaultCacheSizeLimit;
  }
}
