private int getMaxCacheSize(){
  final int maxMemory=(int)Math.min(Runtime.getRuntime().maxMemory(),Integer.MAX_VALUE);
  if (maxMemory < 16 * ByteConstants.MB) {
    return 1 * ByteConstants.MB;
  }
 else   if (maxMemory < 32 * ByteConstants.MB) {
    return 2 * ByteConstants.MB;
  }
 else {
    return 4 * ByteConstants.MB;
  }
}
