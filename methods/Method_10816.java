/** 
 * ??????
 * @param src      ???
 * @param content  ????
 * @param textSize ??????
 * @param color    ??????
 * @param alpha    ???????
 * @param x        ????x
 * @param y        ????y
 * @param recycle  ????
 * @return ?????????
 */
public static Bitmap addTextWatermark(Bitmap src,String content,int textSize,int color,int alpha,float x,float y,boolean recycle){
  if (isEmptyBitmap(src) || content == null) {
    return null;
  }
  Bitmap ret=src.copy(src.getConfig(),true);
  Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
  Canvas canvas=new Canvas(ret);
  paint.setAlpha(alpha);
  paint.setColor(color);
  paint.setTextSize(textSize);
  Rect bounds=new Rect();
  paint.getTextBounds(content,0,content.length(),bounds);
  canvas.drawText(content,x,y,paint);
  if (recycle && !src.isRecycled()) {
    src.recycle();
  }
  return ret;
}
