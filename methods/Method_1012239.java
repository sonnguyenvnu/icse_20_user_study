/** 
 * ??????
 * @param bitmap ?Bitmap
 * @param w      ?
 * @param h      ?
 * @return ??Bitmap
 */
public static Bitmap zoom(Bitmap bitmap,int w,int h){
  int width=bitmap.getWidth();
  int height=bitmap.getHeight();
  Matrix matrix=new Matrix();
  float scaleWidth=((float)w / width);
  float scaleHeight=((float)h / height);
  matrix.postScale(scaleWidth,scaleHeight);
  return Bitmap.createBitmap(bitmap,0,0,width,height,matrix,true);
}
