/** 
 * Returns a mode that specifies when the adaptation workaround should be enabled. <p>When enabled, the workaround queues and discards a blank frame with a resolution whose width and height both equal  {@link #ADAPTATION_WORKAROUND_SLICE_WIDTH_HEIGHT}, to reset the decoder's internal state when a format change occurs. <p>See [Internal: b/27807182]. See <a href="https://github.com/google/ExoPlayer/issues/3257">GitHub issue #3257</a>.
 * @param name The name of the decoder.
 * @return The mode specifying when the adaptation workaround should be enabled.
 */
private @AdaptationWorkaroundMode int codecAdaptationWorkaroundMode(String name){
  if (Util.SDK_INT <= 25 && "OMX.Exynos.avc.dec.secure".equals(name) && (Util.MODEL.startsWith("SM-T585") || Util.MODEL.startsWith("SM-A510") || Util.MODEL.startsWith("SM-A520") || Util.MODEL.startsWith("SM-J700"))) {
    return ADAPTATION_WORKAROUND_MODE_ALWAYS;
  }
 else   if (Util.SDK_INT < 24 && ("OMX.Nvidia.h264.decode".equals(name) || "OMX.Nvidia.h264.decode.secure".equals(name)) && ("flounder".equals(Util.DEVICE) || "flounder_lte".equals(Util.DEVICE) || "grouper".equals(Util.DEVICE) || "tilapia".equals(Util.DEVICE))) {
    return ADAPTATION_WORKAROUND_MODE_SAME_RESOLUTION;
  }
 else {
    return ADAPTATION_WORKAROUND_MODE_NEVER;
  }
}
