/** 
 * ???????(5.0????????,?????)
 * @param activity ????? activity
 * @param color    ??????
 */
@Deprecated public static void setColorDiff(Activity activity,@ColorInt int color){
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
    return;
  }
  activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
  ViewGroup decorView=(ViewGroup)activity.getWindow().getDecorView();
  int count=decorView.getChildCount();
  if (count > 0 && decorView.getChildAt(count - 1) instanceof StatusBarView) {
    decorView.getChildAt(count - 1).setBackgroundColor(color);
  }
 else {
    StatusBarView statusView=createStatusBarView(activity,color);
    decorView.addView(statusView);
  }
  setRootView(activity);
}
