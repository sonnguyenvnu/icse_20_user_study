/** 
 * ?????????
 * @param activity activity
 * @return {@code true}: ??<br> {@code false}: ???
 */
public static boolean isStatusBarExists(Activity activity){
  WindowManager.LayoutParams params=activity.getWindow().getAttributes();
  return (params.flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != WindowManager.LayoutParams.FLAG_FULLSCREEN;
}
