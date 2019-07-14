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
  if ((debugTextPaint == null || debugLinePaint == null) && debug) {
    debugTextPaint=new Paint();
    debugTextPaint.setTextSize(px(12));
    debugTextPaint.setColor(Color.MAGENTA);
    debugTextPaint.setStyle(Style.FILL);
    debugLinePaint=new Paint();
    debugLinePaint.setColor(Color.MAGENTA);
    debugLinePaint.setStyle(Style.STROKE);
    debugLinePaint.setStrokeWidth(px(1));
  }
}
