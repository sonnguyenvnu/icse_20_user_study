private void sendEmailConfirm(String code){
  TLRPC.TL_account_confirmPasswordEmail req=new TLRPC.TL_account_confirmPasswordEmail();
  req.code=code;
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (type == 0 && waitingForEmail) {
      showDoneProgress(false);
    }
    if (error == null) {
      if (getParentActivity() == null) {
        return;
      }
      if (shortPollRunnable != null) {
        AndroidUtilities.cancelRunOnUIThread(shortPollRunnable);
        shortPollRunnable=null;
      }
      AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
      builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> {
        if (type == 0) {
          loadPasswordInfo(false);
          doneItem.setVisibility(View.GONE);
        }
 else {
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.didSetTwoStepPassword,currentPasswordHash,currentPassword.new_algo,currentPassword.new_secure_algo,currentPassword.secure_random,email,hint,null,firstPassword);
          finishFragment();
        }
      }
);
      if (currentPassword != null && currentPassword.has_password) {
        builder.setMessage(LocaleController.getString("YourEmailSuccessText",R.string.YourEmailSuccessText));
      }
 else {
        builder.setMessage(LocaleController.getString("YourPasswordSuccessText",R.string.YourPasswordSuccessText));
      }
      builder.setTitle(LocaleController.getString("YourPasswordSuccess",R.string.YourPasswordSuccess));
      Dialog dialog=showDialog(builder.create());
      if (dialog != null) {
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
      }
    }
 else {
      if (error.text.startsWith("CODE_INVALID")) {
        onFieldError(waitingForEmail ? codeFieldCell.getTextView() : passwordEditText,true);
      }
 else       if (error.text.startsWith("FLOOD_WAIT")) {
        int time=Utilities.parseInt(error.text);
        String timeString;
        if (time < 60) {
          timeString=LocaleController.formatPluralString("Seconds",time);
        }
 else {
          timeString=LocaleController.formatPluralString("Minutes",time / 60);
        }
        showAlertWithText(LocaleController.getString("AppName",R.string.AppName),LocaleController.formatString("FloodWaitTime",R.string.FloodWaitTime,timeString));
      }
 else {
        showAlertWithText(LocaleController.getString("AppName",R.string.AppName),error.text);
      }
    }
  }
),ConnectionsManager.RequestFlagFailOnServerErrors | ConnectionsManager.RequestFlagWithoutLogin);
}
