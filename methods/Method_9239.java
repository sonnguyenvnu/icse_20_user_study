public void switchToAccount(int account){
  if (account == UserConfig.selectedAccount) {
    return;
  }
  ConnectionsManager.getInstance(UserConfig.selectedAccount).setAppPaused(true,false);
  UserConfig.selectedAccount=account;
  UserConfig.getInstance(0).saveConfig(false);
  if (!ApplicationLoader.mainInterfacePaused) {
    ConnectionsManager.getInstance(UserConfig.selectedAccount).setAppPaused(false,false);
  }
}
