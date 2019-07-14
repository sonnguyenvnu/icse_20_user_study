/** 
 * Creates Paint objects once when first needed.
 */
private void createPaints(){
  if (bitmapPaint == null) {
    bitmapPaint=new Paint();
    bitmapPaint.setAntiAlias(true);
    bitmapPaint.setFilterBitmap(true);
    bitmapPaint.setDither(true);
  }
  if (debugPaint == null && debug) {
    debugPaint=new Paint();
    debugPaint.setTextSize(18);
    debugPaint.setColor(Color.MAGENTA);
    debugPaint.setStyle(Style.STROKE);
  }
}
