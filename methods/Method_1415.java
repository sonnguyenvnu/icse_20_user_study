private int getMaxCacheSize(){
  final int maxMemory=Math.min(mActivityManager.getMemoryClass() * ByteConstants.MB,Integer.MAX_VALUE);
  if (maxMemory < 32 * ByteConstants.MB) {
    return 4 * ByteConstants.MB;
  }
 else   if (maxMemory < 64 * ByteConstants.MB) {
    return 6 * ByteConstants.MB;
  }
 else {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
      return 8 * ByteConstants.MB;
    }
 else {
      return maxMemory / 4;
    }
  }
}
