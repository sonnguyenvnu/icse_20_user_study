/** 
 * Our Bitmaps live in ashmem, meaning that they are pinned in androids' shared native memory. Therefore, we are not constrained by the max heap size of the dalvik heap, but we want to make sure we don't use too much memory on low end devices, so that we don't force other background process to be evicted.
 */
private static int getMaxSizeHardCap(){
  final int maxMemory=(int)Math.min(Runtime.getRuntime().maxMemory(),Integer.MAX_VALUE);
  if (maxMemory > 16 * ByteConstants.MB) {
    return maxMemory / 4 * 3;
  }
 else {
    return maxMemory / 2;
  }
}
