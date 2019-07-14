@SuppressLint("WrongConstant") public static void lockOrientation(Activity activity){
  if (activity == null || prevOrientation != -10) {
    return;
  }
  try {
    prevOrientation=activity.getRequestedOrientation();
    WindowManager manager=(WindowManager)activity.getSystemService(Activity.WINDOW_SERVICE);
    if (manager != null && manager.getDefaultDisplay() != null) {
      int rotation=manager.getDefaultDisplay().getRotation();
      int orientation=activity.getResources().getConfiguration().orientation;
      if (rotation == Surface.ROTATION_270) {
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
          activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
 else {
          activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        }
      }
 else       if (rotation == Surface.ROTATION_90) {
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
          activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
        }
 else {
          activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
      }
 else       if (rotation == Surface.ROTATION_0) {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
          activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
 else {
          activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
      }
 else {
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
          activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        }
 else {
          activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
        }
      }
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
