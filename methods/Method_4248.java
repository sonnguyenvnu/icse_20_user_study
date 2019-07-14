/** 
 * Returns whether the decoder may output a non-empty buffer with timestamp 0 as the end of stream buffer. <p>See <a href="https://github.com/google/ExoPlayer/issues/5045">GitHub issue #5045</a>.
 */
private static boolean codecNeedsEosBufferTimestampWorkaround(String codecName){
  return Util.SDK_INT < 21 && "OMX.SEC.mp3.dec".equals(codecName) && "samsung".equals(Util.MANUFACTURER) && (Util.DEVICE.startsWith("baffin") || Util.DEVICE.startsWith("grand") || Util.DEVICE.startsWith("fortuna") || Util.DEVICE.startsWith("gprimelte") || Util.DEVICE.startsWith("j2y18lte") || Util.DEVICE.startsWith("ms01"));
}
