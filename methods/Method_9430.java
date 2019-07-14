private void sendSavePassword(final boolean clear){
  if (!clear && codeFieldCell.getVisibility() == View.VISIBLE) {
    String code=codeFieldCell.getText();
    if (code.length() == 0) {
      shakeView(codeFieldCell);
      return;
    }
    showEditDoneProgress(true,true);
    TLRPC.TL_account_confirmPasswordEmail req=new TLRPC.TL_account_confirmPasswordEmail();
    req.code=code;
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      showEditDoneProgress(true,false);
      if (error == null) {
        if (getParentActivity() == null) {
          return;
        }
        if (shortPollRunnable != null) {
          AndroidUtilities.cancelRunOnUIThread(shortPollRunnable);
          shortPollRunnable=null;
        }
        goToNextStep();
      }
 else {
        if (error.text.startsWith("CODE_INVALID")) {
          shakeView(codeFieldCell);
          codeFieldCell.setText("",false);
        }
 else         if (error.text.startsWith("FLOOD_WAIT")) {
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
 else {
    final TLRPC.TL_account_updatePasswordSettings req=new TLRPC.TL_account_updatePasswordSettings();
    final String email;
    final String firstPassword;
    if (clear) {
      doneItem.setVisibility(View.VISIBLE);
      email=null;
      firstPassword=null;
      req.new_settings=new TLRPC.TL_account_passwordInputSettings();
      req.new_settings.flags=2;
      req.new_settings.email="";
      req.password=new TLRPC.TL_inputCheckPasswordEmpty();
    }
 else {
      firstPassword=inputFields[FIELD_ENTERPASSWORD].getText().toString();
      if (TextUtils.isEmpty(firstPassword)) {
        shakeField(FIELD_ENTERPASSWORD);
        return;
      }
      String secondPassword=inputFields[FIELD_REENTERPASSWORD].getText().toString();
      if (!firstPassword.equals(secondPassword)) {
        try {
          Toast.makeText(getParentActivity(),LocaleController.getString("PasswordDoNotMatch",R.string.PasswordDoNotMatch),Toast.LENGTH_SHORT).show();
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        shakeField(FIELD_REENTERPASSWORD);
        return;
      }
      email=inputFields[FIELD_ENTERPASSWORDEMAIL].getText().toString();
      if (email.length() < 3) {
        shakeField(FIELD_ENTERPASSWORDEMAIL);
        return;
      }
      int dot=email.lastIndexOf('.');
      int dog=email.lastIndexOf('@');
      if (dog < 0 || dot < dog) {
        shakeField(FIELD_ENTERPASSWORDEMAIL);
        return;
      }
      req.password=new TLRPC.TL_inputCheckPasswordEmpty();
      req.new_settings=new TLRPC.TL_account_passwordInputSettings();
      req.new_settings.flags|=1;
      req.new_settings.hint="";
      req.new_settings.new_algo=currentPassword.new_algo;
      if (email.length() > 0) {
        req.new_settings.flags|=2;
        req.new_settings.email=email.trim();
      }
    }
    showEditDoneProgress(true,true);
    Utilities.globalQueue.postRunnable(() -> {
      RequestDelegate requestDelegate=(response,error) -> AndroidUtilities.runOnUIThread(() -> {
        if (error != null && "SRP_ID_INVALID".equals(error.text)) {
          TLRPC.TL_account_getPassword getPasswordReq=new TLRPC.TL_account_getPassword();
          ConnectionsManager.getInstance(currentAccount).sendRequest(getPasswordReq,(response2,error2) -> AndroidUtilities.runOnUIThread(() -> {
            if (error2 == null) {
              currentPassword=(TLRPC.TL_account_password)response2;
              TwoStepVerificationActivity.initPasswordNewAlgo(currentPassword);
              sendSavePassword(clear);
            }
          }
),ConnectionsManager.RequestFlagWithoutLogin);
          return;
        }
        showEditDoneProgress(true,false);
        if (clear) {
          currentPassword.has_password=false;
          currentPassword.current_algo=null;
          delegate.currentPasswordUpdated(currentPassword);
          finishFragment();
        }
 else {
          if (error == null && response instanceof TLRPC.TL_boolTrue) {
            if (getParentActivity() == null) {
              return;
            }
            goToNextStep();
          }
 else           if (error != null) {
            if (error.text.equals("EMAIL_UNCONFIRMED") || error.text.startsWith("EMAIL_UNCONFIRMED_")) {
              emailCodeLength=Utilities.parseInt(error.text);
              AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
              builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> {
                waitingForEmail=true;
                currentPassword.email_unconfirmed_pattern=email;
                updatePasswordFields();
              }
);
              builder.setMessage(LocaleController.getString("YourEmailAlmostThereText",R.string.YourEmailAlmostThereText));
              builder.setTitle(LocaleController.getString("YourEmailAlmostThere",R.string.YourEmailAlmostThere));
              Dialog dialog=showDialog(builder.create());
              if (dialog != null) {
                dialog.setCanceledOnTouchOutside(false);
                dialog.setCancelable(false);
              }
            }
 else {
              if (error.text.equals("EMAIL_INVALID")) {
                showAlertWithText(LocaleController.getString("AppName",R.string.AppName),LocaleController.getString("PasswordEmailInvalid",R.string.PasswordEmailInvalid));
              }
 else               if (error.text.startsWith("FLOOD_WAIT")) {
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
        }
      }
);
      if (!clear) {
        byte[] newPasswordBytes=AndroidUtilities.getStringBytes(firstPassword);
        if (currentPassword.new_algo instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) {
          TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow algo=(TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow)currentPassword.new_algo;
          req.new_settings.new_password_hash=SRPHelper.getVBytes(newPasswordBytes,algo);
          if (req.new_settings.new_password_hash == null) {
            TLRPC.TL_error error=new TLRPC.TL_error();
            error.text="ALGO_INVALID";
            requestDelegate.run(null,error);
          }
          ConnectionsManager.getInstance(currentAccount).sendRequest(req,requestDelegate,ConnectionsManager.RequestFlagFailOnServerErrors | ConnectionsManager.RequestFlagWithoutLogin);
        }
 else {
          TLRPC.TL_error error=new TLRPC.TL_error();
          error.text="PASSWORD_HASH_INVALID";
          requestDelegate.run(null,error);
        }
      }
 else {
        ConnectionsManager.getInstance(currentAccount).sendRequest(req,requestDelegate,ConnectionsManager.RequestFlagFailOnServerErrors | ConnectionsManager.RequestFlagWithoutLogin);
      }
    }
);
  }
}
