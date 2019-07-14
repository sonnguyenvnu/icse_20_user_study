/** 
 * Returns whether the decoder is known to output six audio channels when provided with input with fewer than six channels. <p> See [Internal: b/35655036].
 */
private static boolean codecNeedsDiscardChannelsWorkaround(String codecName){
  return Util.SDK_INT < 24 && "OMX.SEC.aac.dec".equals(codecName) && "samsung".equals(Util.MANUFACTURER) && (Util.DEVICE.startsWith("zeroflte") || Util.DEVICE.startsWith("herolte") || Util.DEVICE.startsWith("heroqlte"));
}
