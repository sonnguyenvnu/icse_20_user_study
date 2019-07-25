/** 
 * ????
 */
public static Bitmap convert(Bitmap bitmap,boolean mIsFrontalCamera){
  int w=bitmap.getWidth();
  int h=bitmap.getHeight();
  Bitmap newbBitmap=Bitmap.createBitmap(w,h,Config.ARGB_8888);
  Canvas cv=new Canvas(newbBitmap);
  Matrix m=new Matrix();
  if (mIsFrontalCamera) {
    m.postScale(-1,1);
  }
  Bitmap bitmap2=Bitmap.createBitmap(bitmap,0,0,w,h,m,true);
  cv.drawBitmap(bitmap2,new Rect(0,0,bitmap2.getWidth(),bitmap2.getHeight()),new Rect(0,0,w,h),null);
  return newbBitmap;
}
