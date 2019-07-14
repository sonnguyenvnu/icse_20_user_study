@SuppressLint("WrongConstant") public static void unlockOrientation(Activity activity){
  if (activity == null) {
    return;
  }
  try {
    if (prevOrientation != -10) {
      activity.setRequestedOrientation(prevOrientation);
      prevOrientation=-10;
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
