/** 
 * Sets FILTER_BITMAP_FLAG flag to Paint.  {@link android.graphics.Paint#FILTER_BITMAP_FLAG}<p>This should generally be on when drawing bitmaps, unless performance-bound (rendering to software canvas) or preferring pixelation artifacts to blurriness when scaling significantly.
 * @param paintFilterBitmap whether to set FILTER_BITMAP_FLAG flag to Paint.
 * @return modified instance
 */
public RoundingParams setPaintFilterBitmap(boolean paintFilterBitmap){
  mPaintFilterBitmap=paintFilterBitmap;
  return this;
}
