private void processDone(){
  if (type == 0) {
    if (!passwordEntered) {
      String oldPassword=passwordEditText.getText().toString();
      if (oldPassword.length() == 0) {
        onFieldError(passwordEditText,false);
        return;
      }
      final byte[] oldPasswordBytes=AndroidUtilities.getStringBytes(oldPassword);
      needShowProgress();
      Utilities.globalQueue.postRunnable(() -> {
        final TLRPC.TL_account_getPasswordSettings req=new TLRPC.TL_account_getPasswordSettings();
        final byte[] x_bytes;
        if (currentPassword.current_algo instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) {
          TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow algo=(TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow)currentPassword.current_algo;
          x_bytes=SRPHelper.getX(oldPasswordBytes,algo);
        }
 else {
          x_bytes=null;
        }
        RequestDelegate requestDelegate=(response,error) -> {
          if (error == null) {
            Utilities.globalQueue.postRunnable(() -> {
              boolean secretOk=checkSecretValues(oldPasswordBytes,(TLRPC.TL_account_passwordSettings)response);
              AndroidUtilities.runOnUIThread(() -> {
                needHideProgress();
                if (secretOk) {
                  currentPasswordHash=x_bytes;
                  passwordEntered=true;
                  AndroidUtilities.hideKeyboard(passwordEditText);
                  updateRows();
                }
 else {
                  AlertsCreator.showUpdateAppAlert(getParentActivity(),LocaleController.getString("UpdateAppAlert",R.string.UpdateAppAlert),true);
                }
              }
);
            }
);
          }
 else {
            AndroidUtilities.runOnUIThread(() -> {
              if ("SRP_ID_INVALID".equals(error.text)) {
                TLRPC.TL_account_getPassword getPasswordReq=new TLRPC.TL_account_getPassword();
                ConnectionsManager.getInstance(currentAccount).sendRequest(getPasswordReq,(response2,error2) -> AndroidUtilities.runOnUIThread(() -> {
                  if (error2 == null) {
                    currentPassword=(TLRPC.TL_account_password)response2;
                    initPasswordNewAlgo(currentPassword);
                    processDone();
                  }
                }
),ConnectionsManager.RequestFlagWithoutLogin);
                return;
              }
              needHideProgress();
              if ("PASSWORD_HASH_INVALID".equals(error.text)) {
                onFieldError(passwordEditText,true);
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
);
          }
        }
;
        if (currentPassword.current_algo instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) {
          TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow algo=(TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow)currentPassword.current_algo;
          req.password=SRPHelper.startCheck(x_bytes,currentPassword.srp_id,currentPassword.srp_B,algo);
          if (req.password == null) {
            TLRPC.TL_error error=new TLRPC.TL_error();
            error.text="ALGO_INVALID";
            requestDelegate.run(null,error);
            return;
          }
          ConnectionsManager.getInstance(currentAccount).sendRequest(req,requestDelegate,ConnectionsManager.RequestFlagFailOnServerErrors | ConnectionsManager.RequestFlagWithoutLogin);
        }
 else {
          TLRPC.TL_error error=new TLRPC.TL_error();
          error.text="PASSWORD_HASH_INVALID";
          requestDelegate.run(null,error);
        }
      }
);
    }
 else     if (waitingForEmail && currentPassword != null) {
      if (codeFieldCell.length() == 0) {
        onFieldError(codeFieldCell.getTextView(),false);
        return;
      }
      sendEmailConfirm(codeFieldCell.getText());
      showDoneProgress(true);
    }
  }
 else   if (type == 1) {
    if (passwordSetState == 0) {
      if (passwordEditText.getText().length() == 0) {
        onFieldError(passwordEditText,false);
        return;
      }
      titleTextView.setText(LocaleController.getString("ReEnterYourPasscode",R.string.ReEnterYourPasscode));
      firstPassword=passwordEditText.getText().toString();
      setPasswordSetState(1);
    }
 else     if (passwordSetState == 1) {
      if (!firstPassword.equals(passwordEditText.getText().toString())) {
        try {
          Toast.makeText(getParentActivity(),LocaleController.getString("PasswordDoNotMatch",R.string.PasswordDoNotMatch),Toast.LENGTH_SHORT).show();
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        onFieldError(passwordEditText,true);
        return;
      }
      setPasswordSetState(2);
    }
 else     if (passwordSetState == 2) {
      hint=passwordEditText.getText().toString();
      if (hint.toLowerCase().equals(firstPassword.toLowerCase())) {
        try {
          Toast.makeText(getParentActivity(),LocaleController.getString("PasswordAsHintError",R.string.PasswordAsHintError),Toast.LENGTH_SHORT).show();
        }
 catch (        Exception e) {
          FileLog.e(e);
        }
        onFieldError(passwordEditText,false);
        return;
      }
      if (!currentPassword.has_recovery) {
        setPasswordSetState(3);
      }
 else {
        email="";
        setNewPassword(false);
      }
    }
 else     if (passwordSetState == 3) {
      email=passwordEditText.getText().toString();
      if (!isValidEmail(email)) {
        onFieldError(passwordEditText,false);
        return;
      }
      setNewPassword(false);
    }
 else     if (passwordSetState == 4) {
      String code=passwordEditText.getText().toString();
      if (code.length() == 0) {
        onFieldError(passwordEditText,false);
        return;
      }
      TLRPC.TL_auth_recoverPassword req=new TLRPC.TL_auth_recoverPassword();
      req.code=code;
      ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
        if (error == null) {
          AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
          builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> {
            NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.didSetTwoStepPassword);
            finishFragment();
          }
);
          builder.setMessage(LocaleController.getString("PasswordReset",R.string.PasswordReset));
          builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
          Dialog dialog=showDialog(builder.create());
          if (dialog != null) {
            dialog.setCanceledOnTouchOutside(false);
            dialog.setCancelable(false);
          }
        }
 else {
          if (error.text.startsWith("CODE_INVALID")) {
            onFieldError(passwordEditText,true);
          }
 else           if (error.text.startsWith("FLOOD_WAIT")) {
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
  }
}
