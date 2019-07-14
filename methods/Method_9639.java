private void loadPasswordInfo(final boolean silent){
  if (!silent) {
    loading=true;
    if (listAdapter != null) {
      listAdapter.notifyDataSetChanged();
    }
  }
  TLRPC.TL_account_getPassword req=new TLRPC.TL_account_getPassword();
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (error == null) {
      loading=false;
      currentPassword=(TLRPC.TL_account_password)response;
      if (!canHandleCurrentPassword(currentPassword,false)) {
        AlertsCreator.showUpdateAppAlert(getParentActivity(),LocaleController.getString("UpdateAppAlert",R.string.UpdateAppAlert),true);
        return;
      }
      if (!silent) {
        passwordEntered=currentPasswordHash != null && currentPasswordHash.length > 0 || !currentPassword.has_password;
      }
      waitingForEmail=!TextUtils.isEmpty(currentPassword.email_unconfirmed_pattern);
      initPasswordNewAlgo(currentPassword);
      if (!paused && closeAfterSet && currentPassword.has_password) {
        TLRPC.PasswordKdfAlgo pendingCurrentAlgo=currentPassword.current_algo;
        TLRPC.SecurePasswordKdfAlgo pendingNewSecureAlgo=currentPassword.new_secure_algo;
        byte[] pendingSecureRandom=currentPassword.secure_random;
        String pendingEmail=currentPassword.has_recovery ? "1" : null;
        String pendingHint=currentPassword.hint != null ? currentPassword.hint : "";
        if (!waitingForEmail && pendingCurrentAlgo != null) {
          NotificationCenter.getInstance(currentAccount).removeObserver(TwoStepVerificationActivity.this,NotificationCenter.didSetTwoStepPassword);
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.didSetTwoStepPassword,null,pendingCurrentAlgo,pendingNewSecureAlgo,pendingSecureRandom,pendingEmail,pendingHint,null,null);
          finishFragment();
        }
      }
    }
    if (type == 0 && !destroyed && shortPollRunnable == null && currentPassword != null && !TextUtils.isEmpty(currentPassword.email_unconfirmed_pattern)) {
      startShortpoll();
    }
    updateRows();
  }
),ConnectionsManager.RequestFlagFailOnServerErrors | ConnectionsManager.RequestFlagWithoutLogin);
}
