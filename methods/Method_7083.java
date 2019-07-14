@Override public boolean onCreate(){
  for (int i=0; i < UserConfig.getActivatedAccountsCount(); i++) {
    NotificationCenter.getInstance(i).addObserver(this,NotificationCenter.fileDidLoad);
  }
  return true;
}
