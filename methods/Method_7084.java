@Override public void shutdown(){
  for (int i=0; i < UserConfig.getActivatedAccountsCount(); i++) {
    NotificationCenter.getInstance(i).removeObserver(this,NotificationCenter.fileDidLoad);
  }
}
