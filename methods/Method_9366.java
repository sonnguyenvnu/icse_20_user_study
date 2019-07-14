private void loadPasswordInfo(){
  TLRPC.TL_account_getPassword req=new TLRPC.TL_account_getPassword();
  int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (response != null) {
      currentPassword=(TLRPC.TL_account_password)response;
      if (!TwoStepVerificationActivity.canHandleCurrentPassword(currentPassword,false)) {
        AlertsCreator.showUpdateAppAlert(getParentActivity(),LocaleController.getString("UpdateAppAlert",R.string.UpdateAppAlert),true);
        return;
      }
      TwoStepVerificationActivity.initPasswordNewAlgo(currentPassword);
      updatePasswordInterface();
      if (inputFieldContainers[FIELD_PASSWORD].getVisibility() == View.VISIBLE) {
        inputFields[FIELD_PASSWORD].requestFocus();
        AndroidUtilities.showKeyboard(inputFields[FIELD_PASSWORD]);
      }
      if (usingSavedPassword == 1) {
        onPasswordDone(true);
      }
    }
  }
));
  ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
}
