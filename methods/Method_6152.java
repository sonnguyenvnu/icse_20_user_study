public static boolean shouldEnableAnimation(){
  if (Build.VERSION.SDK_INT < 26 || Build.VERSION.SDK_INT >= 28) {
    return true;
  }
  PowerManager powerManager=(PowerManager)ApplicationLoader.applicationContext.getSystemService(Context.POWER_SERVICE);
  if (powerManager.isPowerSaveMode()) {
    return false;
  }
  float scale=Settings.Global.getFloat(ApplicationLoader.applicationContext.getContentResolver(),Settings.Global.ANIMATOR_DURATION_SCALE,1.0f);
  if (scale <= 0.0f) {
    return false;
  }
  return true;
}
