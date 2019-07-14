/** 
 * ??????
 */
@TargetApi(Build.VERSION_CODES.KITKAT) private static void transparentStatusBar(Activity activity){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
  }
 else {
    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
  }
}
