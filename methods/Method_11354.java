/** 
 * Called by worker task when preview image is loaded.
 */
private synchronized void onPreviewLoaded(Bitmap previewBitmap){
  debug("onPreviewLoaded");
  if (bitmap != null || imageLoadedSent) {
    previewBitmap.recycle();
    return;
  }
  if (pRegion != null) {
    bitmap=Bitmap.createBitmap(previewBitmap,pRegion.left,pRegion.top,pRegion.width(),pRegion.height());
  }
 else {
    bitmap=previewBitmap;
  }
  bitmapIsPreview=true;
  if (checkReady()) {
    invalidate();
    requestLayout();
  }
}
