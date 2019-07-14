/** 
 * Returns whether the device is known to do post processing by default that isn't compatible with ExoPlayer.
 * @return Whether the device is known to do post processing by default that isn't compatible withExoPlayer.
 */
private static boolean deviceNeedsNoPostProcessWorkaround(){
  return "NVIDIA".equals(Util.MANUFACTURER);
}
