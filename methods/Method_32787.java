@ReactMethod public BigInteger getFreeDiskStorage(){
  try {
    StatFs external=new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
    long availableBlocks;
    long blockSize;
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR2) {
      availableBlocks=external.getAvailableBlocks();
      blockSize=external.getBlockSize();
    }
 else {
      availableBlocks=external.getAvailableBlocksLong();
      blockSize=external.getBlockSizeLong();
    }
    return BigInteger.valueOf(availableBlocks).multiply(BigInteger.valueOf(blockSize));
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return null;
}
