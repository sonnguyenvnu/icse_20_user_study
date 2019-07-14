private static boolean checkGlobalSetting(Context context){
  return Settings.System.getInt(context.getContentResolver(),Settings.System.HAPTIC_FEEDBACK_ENABLED,0) == 1;
}
