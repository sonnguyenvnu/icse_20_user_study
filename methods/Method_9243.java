private void onPasscodeResume(){
  if (lockRunnable != null) {
    AndroidUtilities.cancelRunOnUIThread(lockRunnable);
    lockRunnable=null;
  }
  if (AndroidUtilities.needShowPasscode(true)) {
    showPasscodeActivity();
  }
  if (SharedConfig.lastPauseTime != 0) {
    SharedConfig.lastPauseTime=0;
    SharedConfig.saveConfig();
  }
}
