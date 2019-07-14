private void checkCurrentAccount(){
  if (currentAccount != UserConfig.selectedAccount) {
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.appDidLogout);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.mainUserInfoChanged);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.didUpdateConnectionState);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.needShowAlert);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.wasUnableToFindCurrentLocation);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.openArticle);
    NotificationCenter.getInstance(currentAccount).removeObserver(this,NotificationCenter.hasNewContactsToImport);
  }
  currentAccount=UserConfig.selectedAccount;
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.appDidLogout);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.mainUserInfoChanged);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.didUpdateConnectionState);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.needShowAlert);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.wasUnableToFindCurrentLocation);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.openArticle);
  NotificationCenter.getInstance(currentAccount).addObserver(this,NotificationCenter.hasNewContactsToImport);
  updateCurrentConnectionState(currentAccount);
}
