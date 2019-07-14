/** 
 * ??????????????? <p>?????????????getStatusBarHeight???</p>
 * @param activity activity
 * @return Bitmap
 */
public static Bitmap captureWithoutStatusBar(Activity activity){
  View view=activity.getWindow().getDecorView();
  view.setDrawingCacheEnabled(true);
  view.buildDrawingCache();
  Bitmap bmp=view.getDrawingCache();
  int statusBarHeight=RxBarTool.getStatusBarHeight(activity);
  int width=getScreenWidth(activity);
  int height=getScreenHeight(activity);
  Bitmap ret=Bitmap.createBitmap(bmp,0,statusBarHeight,width,height - statusBarHeight);
  view.destroyDrawingCache();
  return ret;
}
