public static boolean isDeviceInLandScape(Activity activity){
  return activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
}
