/** 
 * Get the current video contrast.
 * @return contrast, in the range from 0.0 to 2.0
 */
public float contrast(){
  return libvlc_video_get_adjust_float(mediaPlayerInstance,libvlc_video_adjust_option_t.libvlc_adjust_Contrast.intValue());
}
