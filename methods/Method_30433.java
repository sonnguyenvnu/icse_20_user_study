@SuppressLint("WakelockTimeout") private void holdLocks(boolean hold){
  if (mWakeLock.isHeld() != hold) {
    if (hold) {
      mWakeLock.acquire();
    }
 else {
      mWakeLock.release();
    }
  }
  boolean holdWifiLock=mNeedWifiLock && hold;
  if (mWifiLock.isHeld() != holdWifiLock) {
    if (holdWifiLock) {
      mWifiLock.acquire();
    }
 else {
      mWifiLock.release();
    }
  }
}
