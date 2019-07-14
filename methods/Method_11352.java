/** 
 * Called by worker task when decoder is ready and image size and EXIF orientation is known.
 */
private synchronized void onTilesInited(ImageRegionDecoder decoder,int sWidth,int sHeight,int sOrientation){
  debug("onTilesInited sWidth=%d, sHeight=%d, sOrientation=%d",sWidth,sHeight,orientation);
  if (this.sWidth > 0 && this.sHeight > 0 && (this.sWidth != sWidth || this.sHeight != sHeight)) {
    reset(false);
    if (bitmap != null) {
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
  }
  this.decoder=decoder;
  this.sWidth=sWidth;
  this.sHeight=sHeight;
  this.sOrientation=sOrientation;
  checkReady();
  if (!checkImageLoaded() && maxTileWidth > 0 && maxTileWidth != TILE_SIZE_AUTO && maxTileHeight > 0 && maxTileHeight != TILE_SIZE_AUTO && getWidth() > 0 && getHeight() > 0) {
    initialiseBaseLayer(new Point(maxTileWidth,maxTileHeight));
  }
  invalidate();
  requestLayout();
}
