private void setCurrentPassword(TLRPC.TL_account_password password){
  if (password.has_password) {
    if (getParentActivity() == null) {
      return;
    }
    goToNextStep();
  }
 else {
    currentPassword=password;
    if (currentPassword != null) {
      waitingForEmail=!TextUtils.isEmpty(currentPassword.email_unconfirmed_pattern);
    }
    updatePasswordFields();
  }
}
