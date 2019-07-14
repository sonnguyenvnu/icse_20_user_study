public static void cancelRunOnUIThread(Runnable runnable){
  ApplicationLoader.applicationHandler.removeCallbacks(runnable);
}
