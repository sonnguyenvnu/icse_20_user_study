/** 
 * ????????.
 */
@SuppressWarnings("deprecation") @SuppressLint("NewApi") public static long getSDCardAvailaleSize(){
  File path=getRootPath();
  StatFs stat=new StatFs(path.getPath());
  long blockSize, availableBlocks;
  if (Build.VERSION.SDK_INT >= 18) {
    blockSize=stat.getBlockSizeLong();
    availableBlocks=stat.getAvailableBlocksLong();
  }
 else {
    blockSize=stat.getBlockSize();
    availableBlocks=stat.getAvailableBlocks();
  }
  return availableBlocks * blockSize;
}
