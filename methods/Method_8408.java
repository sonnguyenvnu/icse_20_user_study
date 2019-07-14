public Bitmap getAnimatedBitmap(){
  if (renderingBitmap != null) {
    return renderingBitmap;
  }
 else   if (nextRenderingBitmap != null) {
    return nextRenderingBitmap;
  }
  return null;
}
