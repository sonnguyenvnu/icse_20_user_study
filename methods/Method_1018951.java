/** 
 * Get the current teletext page.
 * @return page number
 */
public int page(){
  return libvlc_video_get_teletext(mediaPlayerInstance);
}
