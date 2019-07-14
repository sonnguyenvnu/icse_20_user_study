/** 
 * ??MIUI?flyme?6.0???????????
 */
public static void StatusBarDarkMode(Activity activity,int type){
  if (type == 1) {
    MIUISetStatusBarLightMode(activity.getWindow(),false);
  }
 else   if (type == 2) {
    FlymeSetStatusBarLightMode(activity.getWindow(),false);
  }
 else   if (type == 3) {
    activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
  }
}
