public void performLogout(int type){
  if (type == 1) {
    unregistedPush();
    TLRPC.TL_auth_logOut req=new TLRPC.TL_auth_logOut();
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> ConnectionsManager.getInstance(currentAccount).cleanup(false));
  }
 else {
    ConnectionsManager.getInstance(currentAccount).cleanup(type == 2);
  }
  UserConfig.getInstance(currentAccount).clearConfig();
  NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.appDidLogout);
  MessagesStorage.getInstance(currentAccount).cleanup(false);
  cleanup();
  ContactsController.getInstance(currentAccount).deleteUnknownAppAccounts();
}
