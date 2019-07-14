/** 
 * ?DrawerLayout ?????????
 * @param activity       ?????activity
 * @param drawerLayout   DrawerLayout
 * @param color          ??????
 * @param statusBarAlpha ??????
 */
public static void setColorForDrawerLayout(Activity activity,DrawerLayout drawerLayout,@ColorInt int color,int statusBarAlpha){
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
  if (contentLayout.getChildCount() > 0 && contentLayout.getChildAt(0) instanceof StatusBarView) {
    contentLayout.getChildAt(0).setBackgroundColor(calculateStatusColor(color,statusBarAlpha));
  }
 else {
    StatusBarView statusBarView=createStatusBarView(activity,color);
    contentLayout.addView(statusBarView,0);
  }
  if (!(contentLayout instanceof LinearLayout) && contentLayout.getChildAt(1) != null) {
    contentLayout.getChildAt(1).setPadding(contentLayout.getPaddingLeft(),getStatusBarHeight(activity) + contentLayout.getPaddingTop(),contentLayout.getPaddingRight(),contentLayout.getPaddingBottom());
  }
  ViewGroup drawer=(ViewGroup)drawerLayout.getChildAt(1);
  drawerLayout.setFitsSystemWindows(false);
  contentLayout.setFitsSystemWindows(false);
  contentLayout.setClipToPadding(true);
  drawer.setFitsSystemWindows(false);
  addTranslucentView(activity,statusBarAlpha);
}
