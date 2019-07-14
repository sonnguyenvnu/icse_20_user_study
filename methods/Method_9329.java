private void onAuthSuccess(TLRPC.TL_auth_authorization res){
  ConnectionsManager.getInstance(currentAccount).setUserId(res.user.id);
  UserConfig.getInstance(currentAccount).clearConfig();
  MessagesController.getInstance(currentAccount).cleanup();
  UserConfig.getInstance(currentAccount).syncContacts=syncContacts;
  UserConfig.getInstance(currentAccount).setCurrentUser(res.user);
  UserConfig.getInstance(currentAccount).saveConfig(true);
  MessagesStorage.getInstance(currentAccount).cleanup(true);
  ArrayList<TLRPC.User> users=new ArrayList<>();
  users.add(res.user);
  MessagesStorage.getInstance(currentAccount).putUsersAndChats(users,null,true,true);
  MessagesController.getInstance(currentAccount).putUser(res.user,false);
  ContactsController.getInstance(currentAccount).checkAppAccount();
  MessagesController.getInstance(currentAccount).getBlockedUsers(true);
  MessagesController.getInstance(currentAccount).checkProxyInfo(true);
  ConnectionsManager.getInstance(currentAccount).updateDcSettings();
  needFinishActivity();
}
