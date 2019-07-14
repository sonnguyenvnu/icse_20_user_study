public static void runOnUiThread(@NonNull Runnable runnable){
  if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
    runnable.run();
  }
 else {
    sMainHandler.post(runnable);
  }
}
