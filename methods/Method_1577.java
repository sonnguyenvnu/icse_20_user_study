/** 
 * We need a smaller cap for devices with less then 16 MB so that we don't run the risk of evicting other processes from the native heap.
 */
private static int getMaxSizeHardCap(){
  final int maxMemory=(int)Math.min(Runtime.getRuntime().maxMemory(),Integer.MAX_VALUE);
  if (maxMemory < 16 * ByteConstants.MB) {
    return maxMemory / 2;
  }
 else {
    return maxMemory / 4 * 3;
  }
}
