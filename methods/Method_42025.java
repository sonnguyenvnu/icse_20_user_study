/** 
 * This should work temporarily 
 */
private boolean isCurrentMediaImage(){
  return getCurrentMedia().isImage() && !getCurrentMedia().isGif();
}
