/** 
 * ? DrawerLayout ?????????(5.0???????,?????)
 * @param activity     ?????activity
 * @param drawerLayout DrawerLayout
 */
@Deprecated public static void setTranslucentForDrawerLayoutDiff(Activity activity,DrawerLayout drawerLayout){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    ViewGroup contentLayout=(ViewGroup)drawerLayout.getChildAt(0);
    contentLayout.setFitsSystemWindows(true);
    contentLayout.setClipToPadding(true);
    ViewGroup vg=(ViewGroup)drawerLayout.getChildAt(1);
    vg.setFitsSystemWindows(false);
    drawerLayout.setFitsSystemWindows(false);
  }
}
