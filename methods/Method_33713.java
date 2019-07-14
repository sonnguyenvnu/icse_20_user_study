public static void isMainThread(){
  if (DEBUG) {
    Log.e(TAG,"---???????" + (Thread.currentThread() == Looper.getMainLooper().getThread()));
  }
}
