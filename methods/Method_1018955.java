/** 
 * Get the current video gamma.
 * @return gamma value, in the range from 0.01 to 10.0
 */
public float gamma(){
  return libvlc_video_get_adjust_float(mediaPlayerInstance,libvlc_video_adjust_option_t.libvlc_adjust_Gamma.intValue());
}
