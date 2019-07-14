/** 
 * Check if an image has been set. The image may not have been loaded and displayed yet.
 * @return If an image is currently set.
 */
public boolean hasImage(){
  return uri != null || bitmap != null;
}
