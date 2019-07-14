/** 
 * Gets the information about the free storage space, including reserved blocks, either internal or external depends on the given input
 * @param storageType Internal or external storage type
 * @return available space in bytes, -1 if no information is available
 */
@SuppressLint("DeprecatedMethod") public long getFreeStorageSpace(StorageType storageType){
  ensureInitialized();
  maybeUpdateStats();
  StatFs statFS=storageType == StorageType.INTERNAL ? mInternalStatFs : mExternalStatFs;
  if (statFS != null) {
    long blockSize, availableBlocks;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
      blockSize=statFS.getBlockSizeLong();
      availableBlocks=statFS.getFreeBlocksLong();
    }
 else {
      blockSize=statFS.getBlockSize();
      availableBlocks=statFS.getFreeBlocks();
    }
    return blockSize * availableBlocks;
  }
  return -1;
}
