/** 
 * ??????
 * @param src     ???
 * @param recycle ????
 * @return ????
 */
public static Bitmap toRound(Bitmap src,boolean recycle){
  if (isEmptyBitmap(src)) {
    return null;
  }
  int width=src.getWidth();
  int height=src.getHeight();
  int radius=Math.min(width,height) >> 1;
  Bitmap ret=src.copy(src.getConfig(),true);
  Paint paint=new Paint();
  Canvas canvas=new Canvas(ret);
  Rect rect=new Rect(0,0,width,height);
  paint.setAntiAlias(true);
  paint.setColor(Color.TRANSPARENT);
  paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
  canvas.drawARGB(0,0,0,0);
  canvas.drawCircle(width >> 1,height >> 1,radius,paint);
  canvas.drawBitmap(src,rect,rect,paint);
  if (recycle && !src.isRecycled()) {
    src.recycle();
  }
  return ret;
}
