/** 
 * Check if available space in the filesystem is greater than the given threshold. Note that the free space stats are cached and updated in intervals of RESTAT_INTERVAL_MS. If the amount of free space has crossed over the threshold since the last update, it will return incorrect results till the space stats are updated again.
 * @param storageType StorageType (internal or external) to test
 * @param freeSpaceThreshold compare the available free space to this size
 * @return whether free space is lower than the input freeSpaceThreshold,returns true if disk information is not available
 */
public boolean testLowDiskSpace(StorageType storageType,long freeSpaceThreshold){
  ensureInitialized();
  long availableStorageSpace=getAvailableStorageSpace(storageType);
  if (availableStorageSpace > 0) {
    return availableStorageSpace < freeSpaceThreshold;
  }
  return true;
}
