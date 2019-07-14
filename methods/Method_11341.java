/** 
 * Check whether view and image dimensions are known and either a preview, full size image or base layer tiles are loaded. First time, send ready event to listener. The next draw will display an image.
 */
private boolean checkReady(){
  boolean ready=getWidth() > 0 && getHeight() > 0 && sWidth > 0 && sHeight > 0 && (bitmap != null || isBaseLayerReady());
  if (!readySent && ready) {
    preDraw();
    readySent=true;
    onReady();
    if (onImageEventListener != null) {
      onImageEventListener.onReady();
    }
  }
  return ready;
}
