private static void ensureOnUiThread(){
  Preconditions.checkState(Looper.getMainLooper().getThread() == Thread.currentThread());
}
