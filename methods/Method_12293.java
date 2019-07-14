/** 
 * See  {@link Drawable#getOpacity()}
 * @return either {@link PixelFormat#TRANSPARENT} or {@link PixelFormat#OPAQUE}depending on current  {@link Paint} and {@link GifOptions#setInIsOpaque(boolean)} used to construct this Drawable
 */
@Override public int getOpacity(){
  if (!mNativeInfoHandle.isOpaque() || mPaint.getAlpha() < 255) {
    return PixelFormat.TRANSPARENT;
  }
  return PixelFormat.OPAQUE;
}
