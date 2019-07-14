@Override public void onActivityStarted(Activity activity){
  if (++refs == 1) {
    if (System.currentTimeMillis() - enterBackgroundTime < 200) {
      wasInBackground=false;
    }
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("switch to foreground");
    }
    for (    Listener listener : listeners) {
      try {
        listener.onBecameForeground();
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
    }
  }
}
