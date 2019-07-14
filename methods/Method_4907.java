/** 
 * Returns whether the decoder is known to fail when adapting, despite advertising itself as an adaptive decoder.
 * @param name The decoder name.
 * @return True if the decoder is known to fail when adapting.
 */
private static boolean codecNeedsDisableAdaptationWorkaround(String name){
  return Util.SDK_INT <= 22 && ("ODROID-XU3".equals(Util.MODEL) || "Nexus 10".equals(Util.MODEL)) && ("OMX.Exynos.AVC.Decoder".equals(name) || "OMX.Exynos.AVC.Decoder.secure".equals(name));
}
