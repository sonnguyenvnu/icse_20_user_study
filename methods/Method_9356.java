private void updateRows(){
  rowCount=0;
  passcodeRow=rowCount++;
  changePasscodeRow=rowCount++;
  passcodeDetailRow=rowCount++;
  if (SharedConfig.passcodeHash.length() > 0) {
    try {
      if (Build.VERSION.SDK_INT >= 23) {
        FingerprintManagerCompat fingerprintManager=FingerprintManagerCompat.from(ApplicationLoader.applicationContext);
        if (fingerprintManager.isHardwareDetected()) {
          fingerprintRow=rowCount++;
        }
      }
    }
 catch (    Throwable e) {
      FileLog.e(e);
    }
    autoLockRow=rowCount++;
    autoLockDetailRow=rowCount++;
    captureRow=rowCount++;
    captureDetailRow=rowCount++;
  }
 else {
    captureRow=-1;
    captureDetailRow=-1;
    fingerprintRow=-1;
    autoLockRow=-1;
    autoLockDetailRow=-1;
  }
}
