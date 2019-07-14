/** 
 * ??????
 * @param src     ???
 * @param recycle ????
 * @return ???
 */
public static Bitmap toGray(Bitmap src,boolean recycle){
  if (isEmptyBitmap(src)) {
    return null;
  }
  Bitmap grayBitmap=Bitmap.createBitmap(src.getWidth(),src.getHeight(),Bitmap.Config.RGB_565);
  Canvas canvas=new Canvas(grayBitmap);
  Paint paint=new Paint();
  ColorMatrix colorMatrix=new ColorMatrix();
  colorMatrix.setSaturation(0);
  ColorMatrixColorFilter colorMatrixColorFilter=new ColorMatrixColorFilter(colorMatrix);
  paint.setColorFilter(colorMatrixColorFilter);
  canvas.drawBitmap(src,0,0,paint);
  if (recycle && !src.isRecycled()) {
    src.recycle();
  }
  return grayBitmap;
}
