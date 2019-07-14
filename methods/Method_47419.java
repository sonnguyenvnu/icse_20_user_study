/** 
 * Force disables screen rotation. Useful when we're temporarily in activity because of external intent, and don't have to really deal much with filesystem.
 */
public static void disableScreenRotation(MainActivity mainActivity){
  int screenOrientation=mainActivity.getResources().getConfiguration().orientation;
  if (screenOrientation == Configuration.ORIENTATION_LANDSCAPE) {
    mainActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
  }
 else   if (screenOrientation == Configuration.ORIENTATION_PORTRAIT) {
    mainActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
  }
}
