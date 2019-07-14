/** 
 * Check whether either the full size bitmap or base layer tiles are loaded. First time, send image loaded event to listener.
 */
private boolean checkImageLoaded(){
  boolean imageLoaded=isBaseLayerReady();
  if (!imageLoadedSent && imageLoaded) {
    preDraw();
    imageLoadedSent=true;
    onImageLoaded();
    if (onImageEventListener != null) {
      onImageEventListener.onImageLoaded();
    }
  }
  return imageLoaded;
}
