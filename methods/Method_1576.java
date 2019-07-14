/** 
 * {@link NativeMemoryChunkPool} manages memory on the native heap, so we don't need as strictcaps as we would if we were on the Dalvik heap. However, since native memory OOMs are significantly more problematic than Dalvik OOMs, we would like to stay conservative.
 */
private static int getMaxSizeSoftCap(){
  final int maxMemory=(int)Math.min(Runtime.getRuntime().maxMemory(),Integer.MAX_VALUE);
  if (maxMemory < 16 * ByteConstants.MB) {
    return 3 * ByteConstants.MB;
  }
 else   if (maxMemory < 32 * ByteConstants.MB) {
    return 6 * ByteConstants.MB;
  }
 else {
    return 12 * ByteConstants.MB;
  }
}
