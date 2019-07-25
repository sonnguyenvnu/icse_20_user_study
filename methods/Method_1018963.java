/** 
 * Check whether or not there is an overlay component currently enabled.
 * @return true if there is an overlay enabled, otherwise false
 */
public boolean enabled(){
  return overlay != null && overlay.isVisible();
}
