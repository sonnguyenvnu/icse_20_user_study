private void saveName(){
  if (!checkUserName(firstNameField.getText().toString(),true)) {
    return;
  }
  TLRPC.User user=UserConfig.getInstance(currentAccount).getCurrentUser();
  if (getParentActivity() == null || user == null) {
    return;
  }
  String currentName=user.username;
  if (currentName == null) {
    currentName="";
  }
  String newName=firstNameField.getText().toString();
  if (currentName.equals(newName)) {
    finishFragment();
    return;
  }
  final AlertDialog progressDialog=new AlertDialog(getParentActivity(),3);
  final TLRPC.TL_account_updateUsername req=new TLRPC.TL_account_updateUsername();
  req.username=newName;
  NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.updateInterfaces,MessagesController.UPDATE_MASK_NAME);
  final int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error == null) {
      final TLRPC.User user1=(TLRPC.User)response;
      AndroidUtilities.runOnUIThread(() -> {
        try {
          progressDialog.dismiss();
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        ArrayList<TLRPC.User> users=new ArrayList<>();
        users.add(user1);
        MessagesController.getInstance(currentAccount).putUsers(users,false);
        MessagesStorage.getInstance(currentAccount).putUsersAndChats(users,null,false,true);
        UserConfig.getInstance(currentAccount).saveConfig(true);
        finishFragment();
      }
);
    }
 else {
      AndroidUtilities.runOnUIThread(() -> {
        try {
          progressDialog.dismiss();
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        AlertsCreator.processError(currentAccount,error,ChangeUsernameActivity.this,req);
      }
);
    }
  }
,ConnectionsManager.RequestFlagFailOnServerErrors);
  ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
  progressDialog.setOnCancelListener(dialog -> ConnectionsManager.getInstance(currentAccount).cancelRequest(reqId,true));
  progressDialog.show();
}
