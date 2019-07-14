/** 
 * Gets the information about the total storage space, either internal or external depends on the given input
 * @param storageType Internal or external storage type
 * @return available space in bytes, -1 if no information is available
 */
@SuppressLint("DeprecatedMethod") public long getTotalStorageSpace(StorageType storageType){
  ensureInitialized();
  maybeUpdateStats();
  StatFs statFS=storageType == StorageType.INTERNAL ? mInternalStatFs : mExternalStatFs;
  if (statFS != null) {
    long blockSize, totalBlocks;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
      blockSize=statFS.getBlockSizeLong();
      totalBlocks=statFS.getBlockCountLong();
    }
 else {
      blockSize=statFS.getBlockSize();
      totalBlocks=statFS.getBlockCount();
    }
    return blockSize * totalBlocks;
  }
  return -1;
}
