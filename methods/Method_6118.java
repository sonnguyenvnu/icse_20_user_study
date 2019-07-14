public static void runOnUIThread(Runnable runnable,long delay){
  if (delay == 0) {
    ApplicationLoader.applicationHandler.post(runnable);
  }
 else {
    ApplicationLoader.applicationHandler.postDelayed(runnable,delay);
  }
}
