/** 
 * Called by worker task when a tile has loaded. Redraws the view.
 */
private synchronized void onTileLoaded(){
  debug("onTileLoaded");
  checkReady();
  checkImageLoaded();
  if (isBaseLayerReady() && bitmap != null) {
    if (!bitmapIsCached) {
      bitmap.recycle();
    }
    bitmap=null;
    if (onImageEventListener != null && bitmapIsCached) {
      onImageEventListener.onPreviewReleased();
    }
    bitmapIsPreview=false;
    bitmapIsCached=false;
  }
  invalidate();
}
