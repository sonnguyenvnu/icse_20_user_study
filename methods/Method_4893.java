/** 
 * Returns whether the decoder is known to fail when flushed. <p> If true is returned, the renderer will work around the issue by releasing the decoder and instantiating a new one rather than flushing the current instance. <p> See [Internal: b/8347958, b/8543366].
 * @param name The name of the decoder.
 * @return True if the decoder is known to fail when flushed.
 */
private static boolean codecNeedsFlushWorkaround(String name){
  return Util.SDK_INT < 18 || (Util.SDK_INT == 18 && ("OMX.SEC.avc.dec".equals(name) || "OMX.SEC.avc.dec.secure".equals(name))) || (Util.SDK_INT == 19 && Util.MODEL.startsWith("SM-G800") && ("OMX.Exynos.avc.dec".equals(name) || "OMX.Exynos.avc.dec.secure".equals(name)));
}
