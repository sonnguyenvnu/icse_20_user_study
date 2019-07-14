/** 
 * ? DrawerLayout ?????????
 * @param activity     ?????activity
 * @param drawerLayout DrawerLayout
 */
public static void setTransparentForDrawerLayout(Activity activity,DrawerLayout drawerLayout){
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
    return;
  }
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
  }
 else {
    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
  }
  ViewGroup contentLayout=(ViewGroup)drawerLayout.getChildAt(0);
  if (!(contentLayout instanceof LinearLayout) && contentLayout.getChildAt(1) != null) {
    contentLayout.getChildAt(1).setPadding(0,getStatusBarHeight(activity),0,0);
  }
  ViewGroup drawer=(ViewGroup)drawerLayout.getChildAt(1);
  drawerLayout.setFitsSystemWindows(false);
  contentLayout.setFitsSystemWindows(false);
  contentLayout.setClipToPadding(true);
  drawer.setFitsSystemWindows(false);
}
