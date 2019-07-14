/** 
 * ???? ImageView ??????????
 * @param activity       ?????activity
 * @param statusBarAlpha ??????
 * @param needOffsetView ??????? View
 */
public static void setTranslucentForImageView(Activity activity,int statusBarAlpha,View needOffsetView){
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
    return;
  }
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
    activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    if (activity instanceof TabActivity) {
      activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }
  }
 else {
    activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
  }
  addTranslucentView(activity,statusBarAlpha);
  if (needOffsetView != null) {
    ViewGroup.MarginLayoutParams layoutParams=(ViewGroup.MarginLayoutParams)needOffsetView.getLayoutParams();
    if (layoutParams != null) {
      layoutParams.setMargins(0,getStatusBarHeight(activity),0,0);
    }
  }
}
