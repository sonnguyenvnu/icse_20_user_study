/** 
 * Try to render the frame to the given target bitmap. If the rendering fails, the target bitmap reference will be closed and false is returned. If rendering succeeds, the target bitmap reference can be drawn and has to be manually closed after drawing has been completed.
 * @param frameNumber the frame number to render
 * @param targetBitmap the target bitmap
 * @return true if rendering successful
 */
private boolean renderFrameInBitmap(int frameNumber,@Nullable CloseableReference<Bitmap> targetBitmap){
  if (!CloseableReference.isValid(targetBitmap)) {
    return false;
  }
  boolean frameRendered=mBitmapFrameRenderer.renderFrame(frameNumber,targetBitmap.get());
  if (!frameRendered) {
    CloseableReference.closeSafely(targetBitmap);
  }
  return frameRendered;
}
