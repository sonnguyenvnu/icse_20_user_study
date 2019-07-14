/** 
 * Gets the information about the available storage space either internal or external depends on the give input
 * @param storageType Internal or external storage type
 * @return available space in bytes, 0 if no information is available
 */
@SuppressLint("DeprecatedMethod") public long getAvailableStorageSpace(StorageType storageType){
  ensureInitialized();
  maybeUpdateStats();
  StatFs statFS=storageType == StorageType.INTERNAL ? mInternalStatFs : mExternalStatFs;
  if (statFS != null) {
    long blockSize, availableBlocks;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
      blockSize=statFS.getBlockSizeLong();
      availableBlocks=statFS.getAvailableBlocksLong();
    }
 else {
      blockSize=statFS.getBlockSize();
      availableBlocks=statFS.getAvailableBlocks();
    }
    return blockSize * availableBlocks;
  }
  return 0;
}
