/** 
 * drawable?bitmap
 * @param drawable drawable??
 * @return bitmap??
 */
public static Bitmap drawable2Bitmap(Drawable drawable){
  int w=drawable.getIntrinsicWidth();
  int h=drawable.getIntrinsicHeight();
  Bitmap.Config config=drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
  Bitmap bitmap=Bitmap.createBitmap(w,h,config);
  Canvas canvas=new Canvas(bitmap);
  drawable.setBounds(0,0,w,h);
  drawable.draw(canvas);
  return bitmap;
}
