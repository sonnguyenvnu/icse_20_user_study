/** 
 * Helper method that draws the given bitmap on the canvas respecting the bounds (if set). If rendering was successful, it notifies the cache that the frame has been rendered with the given bitmap. In addition, it will notify the  {@link FrameListener} if set.
 * @param frameNumber the current frame number passed to the cache
 * @param bitmapReference the bitmap to draw
 * @param canvas the canvas to draw an
 * @param frameType the {@link FrameType} to be rendered
 * @return true if the bitmap has been drawn
 */
private boolean drawBitmapAndCache(int frameNumber,@Nullable CloseableReference<Bitmap> bitmapReference,Canvas canvas,@FrameType int frameType){
  if (!CloseableReference.isValid(bitmapReference)) {
    return false;
  }
  if (mBounds == null) {
    canvas.drawBitmap(bitmapReference.get(),0f,0f,mPaint);
  }
 else {
    canvas.drawBitmap(bitmapReference.get(),null,mBounds,mPaint);
  }
  if (frameType != FRAME_TYPE_FALLBACK) {
    mBitmapFrameCache.onFrameRendered(frameNumber,bitmapReference,frameType);
  }
  if (mFrameListener != null) {
    mFrameListener.onFrameDrawn(this,frameNumber,frameType);
  }
  return true;
}
