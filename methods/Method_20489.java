/** 
 * Called by worker task when full size image bitmap is ready (tiling is disabled).
 */
private synchronized void onImageLoaded(Bitmap bitmap,int sOrientation,boolean bitmapIsCached){
  debug("onImageLoaded");
  if (this.sWidth > 0 && this.sHeight > 0 && (this.sWidth != bitmap.getWidth() || this.sHeight != bitmap.getHeight())) {
    reset(false);
  }
  if (this.bitmap != null && !this.bitmapIsCached) {
    this.bitmap.recycle();
  }
  if (this.bitmap != null && this.bitmapIsCached && onImageEventListener != null) {
    onImageEventListener.onPreviewReleased();
  }
  this.bitmapIsPreview=false;
  this.bitmapIsCached=bitmapIsCached;
  this.bitmap=bitmap;
  this.sWidth=bitmap.getWidth();
  this.sHeight=bitmap.getHeight();
  this.sOrientation=sOrientation;
  boolean ready=checkReady();
  boolean imageLoaded=checkImageLoaded();
  if (ready || imageLoaded) {
    invalidate();
    requestLayout();
  }
}
