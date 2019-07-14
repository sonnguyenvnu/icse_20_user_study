/** 
 * ??????????.
 */
@SuppressLint("NewApi") @SuppressWarnings("deprecation") public static long getDirSize(String path){
  StatFs stat=new StatFs(path);
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
