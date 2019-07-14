public static void ensureMainThread(){
  if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
    throw new CalledFromWrongThreadException("Methods that affect the view hierarchy can can only be called from the main thread.");
  }
}
