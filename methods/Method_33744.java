public static void setLightMode(Activity activity){
  setMIUIStatusBarDarkIcon(activity,true);
  setMeizuStatusBarDarkIcon(activity,true);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
  }
}
