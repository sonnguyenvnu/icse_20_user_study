/** 
 * ??????????????
 * @param activity activity
 * @return Bitmap
 */
public static Bitmap captureWithStatusBar(Activity activity){
  View view=activity.getWindow().getDecorView();
  view.setDrawingCacheEnabled(true);
  view.buildDrawingCache();
  Bitmap bmp=view.getDrawingCache();
  int width=getScreenWidth(activity);
  int height=getScreenHeight(activity);
  Bitmap ret=Bitmap.createBitmap(bmp,0,0,width,height);
  view.destroyDrawingCache();
  return ret;
}
