public static boolean needShowPasscode(boolean reset){
  boolean wasInBackground=ForegroundDetector.getInstance().isWasInBackground(reset);
  if (reset) {
    ForegroundDetector.getInstance().resetBackgroundVar();
  }
  return SharedConfig.passcodeHash.length() > 0 && wasInBackground && (SharedConfig.appLocked || SharedConfig.autoLockIn != 0 && SharedConfig.lastPauseTime != 0 && !SharedConfig.appLocked && (SharedConfig.lastPauseTime + SharedConfig.autoLockIn) <= ConnectionsManager.getInstance(UserConfig.selectedAccount).getCurrentTime() || ConnectionsManager.getInstance(UserConfig.selectedAccount).getCurrentTime() + 5 < SharedConfig.lastPauseTime);
}
