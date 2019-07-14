/** 
 * Resize the bitmap
 * @param bitmap ????
 * @param width ??
 * @param height ??
 * @return ?????????
 */
public static Bitmap zoomBitmap(Bitmap bitmap,int width,int height){
  int w=bitmap.getWidth();
  int h=bitmap.getHeight();
  Matrix matrix=new Matrix();
  float scaleWidth=((float)width / w);
  float scaleHeight=((float)height / h);
  matrix.postScale(scaleWidth,scaleHeight);
  return Bitmap.createBitmap(bitmap,0,0,w,h,matrix,true);
}
