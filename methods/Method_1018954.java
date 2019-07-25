/** 
 * Get the current video saturation.
 * @return saturation, in the range from 0.0 to 3.0
 */
public float saturation(){
  return libvlc_video_get_adjust_float(mediaPlayerInstance,libvlc_video_adjust_option_t.libvlc_adjust_Saturation.intValue());
}
