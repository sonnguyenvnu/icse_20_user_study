/** 
 * Get the current video hue.
 * @return hue, in the range from -180.0 to 180.0
 */
public float hue(){
  return libvlc_video_get_adjust_float(mediaPlayerInstance,libvlc_video_adjust_option_t.libvlc_adjust_Hue.intValue());
}
