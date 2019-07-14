/** 
 * ??????
 * @param src         ???
 * @param borderWidth ????
 * @param color       ??????
 * @param recycle     ????
 * @return ??????
 */
public static Bitmap addFrame(Bitmap src,int borderWidth,int color,boolean recycle){
  if (isEmptyBitmap(src)) {
    return null;
  }
  int newWidth=src.getWidth() + borderWidth >> 1;
  int newHeight=src.getHeight() + borderWidth >> 1;
  Bitmap ret=Bitmap.createBitmap(newWidth,newHeight,src.getConfig());
  Canvas canvas=new Canvas(ret);
  Rect rec=canvas.getClipBounds();
  Paint paint=new Paint();
  paint.setColor(color);
  paint.setStyle(Paint.Style.STROKE);
  paint.setStrokeWidth(borderWidth);
  canvas.drawRect(rec,paint);
  canvas.drawBitmap(src,borderWidth,borderWidth,null);
  if (recycle && !src.isRecycled()) {
    src.recycle();
  }
  return ret;
}
