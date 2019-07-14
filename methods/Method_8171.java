private void saveName(){
  TLRPC.User currentUser=UserConfig.getInstance(currentAccount).getCurrentUser();
  if (currentUser == null || lastNameField.getText() == null || firstNameField.getText() == null) {
    return;
  }
  String newFirst=firstNameField.getText().toString();
  String newLast=lastNameField.getText().toString();
  if (currentUser.first_name != null && currentUser.first_name.equals(newFirst) && currentUser.last_name != null && currentUser.last_name.equals(newLast)) {
    return;
  }
  TLRPC.TL_account_updateProfile req=new TLRPC.TL_account_updateProfile();
  req.flags=3;
  currentUser.first_name=req.first_name=newFirst;
  currentUser.last_name=req.last_name=newLast;
  TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(UserConfig.getInstance(currentAccount).getClientUserId());
  if (user != null) {
    user.first_name=req.first_name;
    user.last_name=req.last_name;
  }
  UserConfig.getInstance(currentAccount).saveConfig(true);
  NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.mainUserInfoChanged);
  NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,MessagesController.UPDATE_MASK_NAME);
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,new RequestDelegate(){
    @Override public void run(    TLObject response,    TLRPC.TL_error error){
    }
  }
);
}
