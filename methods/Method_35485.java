public static void setLightStatusBar(@NonNull View view){
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    int flags=view.getSystemUiVisibility();
    flags|=View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
    view.setSystemUiVisibility(flags);
  }
}
