private synchronized void createWakeLock(){
  if (mWakeLock == null) {
    PowerManager pm=(PowerManager)getSystemService(Context.POWER_SERVICE);
    mWakeLock=pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"MMS Connectivity");
    mWakeLock.setReferenceCounted(false);
  }
}
