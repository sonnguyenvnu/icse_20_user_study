private void saveName(){
  final TLRPC.UserFull userFull=MessagesController.getInstance(currentAccount).getUserFull(UserConfig.getInstance(currentAccount).getClientUserId());
  if (getParentActivity() == null || userFull == null) {
    return;
  }
  String currentName=userFull.about;
  if (currentName == null) {
    currentName="";
  }
  final String newName=firstNameField.getText().toString().replace("\n","");
  if (currentName.equals(newName)) {
    finishFragment();
    return;
  }
  final AlertDialog progressDialog=new AlertDialog(getParentActivity(),3);
  final TLRPC.TL_account_updateProfile req=new TLRPC.TL_account_updateProfile();
  req.about=newName;
  req.flags|=4;
  final int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> {
    if (error == null) {
      final TLRPC.User user=(TLRPC.User)response;
      AndroidUtilities.runOnUIThread(() -> {
        try {
          progressDialog.dismiss();
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        userFull.about=newName;
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.userInfoDidLoad,user.id,userFull,null);
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
        AlertsCreator.processError(currentAccount,error,ChangeBioActivity.this,req);
      }
);
    }
  }
,ConnectionsManager.RequestFlagFailOnServerErrors);
  ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
  progressDialog.setOnCancelListener(dialog -> ConnectionsManager.getInstance(currentAccount).cancelRequest(reqId,true));
  progressDialog.show();
}
