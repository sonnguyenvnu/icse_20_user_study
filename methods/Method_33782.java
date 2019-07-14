/** 
 * ?DrawerLayout ?????????(5.0????????,?????)
 * @param activity     ?????activity
 * @param drawerLayout DrawerLayout
 * @param color        ??????
 */
@Deprecated public static void setColorForDrawerLayoutDiff(Activity activity,DrawerLayout drawerLayout,@ColorInt int color){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    ViewGroup contentLayout=(ViewGroup)drawerLayout.getChildAt(0);
    if (contentLayout.getChildCount() > 0 && contentLayout.getChildAt(0) instanceof StatusBarView) {
      contentLayout.getChildAt(0).setBackgroundColor(calculateStatusColor(color,DEFAULT_STATUS_BAR_ALPHA));
    }
 else {
      StatusBarView statusBarView=createStatusBarView(activity,color);
      contentLayout.addView(statusBarView,0);
    }
    if (!(contentLayout instanceof LinearLayout) && contentLayout.getChildAt(1) != null) {
      contentLayout.getChildAt(1).setPadding(0,getStatusBarHeight(activity),0,0);
    }
    ViewGroup drawer=(ViewGroup)drawerLayout.getChildAt(1);
    drawerLayout.setFitsSystemWindows(false);
    contentLayout.setFitsSystemWindows(false);
    contentLayout.setClipToPadding(true);
    drawer.setFitsSystemWindows(false);
  }
}
