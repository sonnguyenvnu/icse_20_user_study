private void onFinish(){
  if (finished) {
    return;
  }
  finished=true;
  if (lockRunnable != null) {
    AndroidUtilities.cancelRunOnUIThread(lockRunnable);
    lockRunnable=null;
  }
  if (currentAccount != -1) {
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.appDidLogout);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.mainUserInfoChanged);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.didUpdateConnectionState);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.needShowAlert);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.wasUnableToFindCurrentLocation);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.openArticle);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.hasNewContactsToImport);
  }
  NotificationCenter.getGlobalInstance().removeObserver(this,NotificationCenter.needShowAlert);
  NotificationCenter.getGlobalInstance().removeObserver(this,NotificationCenter.didSetNewWallpapper);
  NotificationCenter.getGlobalInstance().removeObserver(this,NotificationCenter.suggestedLangpack);
  NotificationCenter.getGlobalInstance().removeObserver(this,NotificationCenter.reloadInterface);
  NotificationCenter.getGlobalInstance().removeObserver(this,NotificationCenter.didSetNewTheme);
  NotificationCenter.getGlobalInstance().removeObserver(this,NotificationCenter.needSetDayNightTheme);
  NotificationCenter.getGlobalInstance().removeObserver(this,NotificationCenter.closeOtherAppActivities);
  NotificationCenter.getGlobalInstance().removeObserver(this,NotificationCenter.didSetPasscode);
  NotificationCenter.getGlobalInstance().removeObserver(this,NotificationCenter.notificationsCountUpdated);
}
