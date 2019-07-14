public static void setWaitingForCall(boolean value){
synchronized (callLock) {
    try {
      if (value) {
        if (callReceiver == null) {
          final IntentFilter filter=new IntentFilter(TelephonyManager.ACTION_PHONE_STATE_CHANGED);
          ApplicationLoader.applicationContext.registerReceiver(callReceiver=new CallReceiver(),filter);
        }
      }
 else {
        if (callReceiver != null) {
          ApplicationLoader.applicationContext.unregisterReceiver(callReceiver);
          callReceiver=null;
        }
      }
    }
 catch (    Exception ignore) {
    }
    waitingForCall=value;
  }
}
