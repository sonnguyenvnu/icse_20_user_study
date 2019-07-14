public void withCause(Exception cause){
  if (BuildConfig.DEBUG) {
    Log.println(priority,TAG,Log.getStackTraceString(cause));
  }
}
