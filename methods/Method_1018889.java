/** 
 * Get the current chapter.
 * @return chapter number, where zero is the first chatper, or -1 if no media
 */
public int chapter(){
  return libvlc_media_player_get_chapter(mediaPlayerInstance);
}
