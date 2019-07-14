private void checkPassword(){
  if (UserConfig.getInstance(currentAccount).tmpPassword != null) {
    if (UserConfig.getInstance(currentAccount).tmpPassword.valid_until < ConnectionsManager.getInstance(currentAccount).getCurrentTime() + 60) {
      UserConfig.getInstance(currentAccount).tmpPassword=null;
      UserConfig.getInstance(currentAccount).saveConfig(false);
    }
  }
  if (UserConfig.getInstance(currentAccount).tmpPassword != null) {
    sendData();
    return;
  }
  if (inputFields[FIELD_SAVEDPASSWORD].length() == 0) {
    Vibrator v=(Vibrator)ApplicationLoader.applicationContext.getSystemService(Context.VIBRATOR_SERVICE);
    if (v != null) {
      v.vibrate(200);
    }
    AndroidUtilities.shakeView(inputFields[FIELD_SAVEDPASSWORD],2,0);
    return;
  }
  final String password=inputFields[FIELD_SAVEDPASSWORD].getText().toString();
  showEditDoneProgress(true,true);
  setDonePressed(true);
  final TLRPC.TL_account_getPassword req=new TLRPC.TL_account_getPassword();
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (error == null) {
      TLRPC.TL_account_password currentPassword=(TLRPC.TL_account_password)response;
      if (!TwoStepVerificationActivity.canHandleCurrentPassword(currentPassword,false)) {
        AlertsCreator.showUpdateAppAlert(getParentActivity(),LocaleController.getString("UpdateAppAlert",R.string.UpdateAppAlert),true);
        return;
      }
      if (!currentPassword.has_password) {
        passwordOk=false;
        goToNextStep();
      }
 else {
        byte[] passwordBytes=AndroidUtilities.getStringBytes(password);
        Utilities.globalQueue.postRunnable(() -> {
          final byte[] x_bytes;
          if (currentPassword.current_algo instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) {
            TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow algo=(TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow)currentPassword.current_algo;
            x_bytes=SRPHelper.getX(passwordBytes,algo);
          }
 else {
            x_bytes=null;
          }
          final TLRPC.TL_account_getTmpPassword req1=new TLRPC.TL_account_getTmpPassword();
          req1.period=60 * 30;
          RequestDelegate requestDelegate=(response1,error1) -> AndroidUtilities.runOnUIThread(() -> {
            showEditDoneProgress(true,false);
            setDonePressed(false);
            if (response1 != null) {
              passwordOk=true;
              UserConfig.getInstance(currentAccount).tmpPassword=(TLRPC.TL_account_tmpPassword)response1;
              UserConfig.getInstance(currentAccount).saveConfig(false);
              goToNextStep();
            }
 else {
              if (error1.text.equals("PASSWORD_HASH_INVALID")) {
                Vibrator v=(Vibrator)ApplicationLoader.applicationContext.getSystemService(Context.VIBRATOR_SERVICE);
                if (v != null) {
                  v.vibrate(200);
                }
                AndroidUtilities.shakeView(inputFields[FIELD_SAVEDPASSWORD],2,0);
                inputFields[FIELD_SAVEDPASSWORD].setText("");
              }
 else {
                AlertsCreator.processError(currentAccount,error1,PaymentFormActivity.this,req1);
              }
            }
          }
);
          if (currentPassword.current_algo instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) {
            TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow algo=(TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow)currentPassword.current_algo;
            req1.password=SRPHelper.startCheck(x_bytes,currentPassword.srp_id,currentPassword.srp_B,algo);
            if (req1.password == null) {
              TLRPC.TL_error error2=new TLRPC.TL_error();
              error2.text="ALGO_INVALID";
              requestDelegate.run(null,error2);
              return;
            }
            ConnectionsManager.getInstance(currentAccount).sendRequest(req1,requestDelegate,ConnectionsManager.RequestFlagFailOnServerErrors | ConnectionsManager.RequestFlagWithoutLogin);
          }
 else {
            TLRPC.TL_error error2=new TLRPC.TL_error();
            error2.text="PASSWORD_HASH_INVALID";
            requestDelegate.run(null,error2);
          }
        }
);
      }
    }
 else {
      AlertsCreator.processError(currentAccount,error,PaymentFormActivity.this,req);
      showEditDoneProgress(true,false);
      setDonePressed(false);
    }
  }
),ConnectionsManager.RequestFlagFailOnServerErrors);
}
