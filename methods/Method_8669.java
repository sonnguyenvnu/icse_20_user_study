@Override public void onActivityStopped(Activity activity){
  if (--refs == 0) {
    enterBackgroundTime=System.currentTimeMillis();
    wasInBackground=true;
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("switch to background");
    }
    for (    Listener listener : listeners) {
      try {
        listener.onBecameBackground();
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
  }
}
