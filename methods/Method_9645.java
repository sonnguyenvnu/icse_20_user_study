private void setNewPassword(final boolean clear){
  if (clear && waitingForEmail && currentPassword.has_password) {
    needShowProgress();
    TLRPC.TL_account_cancelPasswordEmail req=new TLRPC.TL_account_cancelPasswordEmail();
    ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      needHideProgress();
      if (error == null) {
        loadPasswordInfo(false);
        NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.didRemoveTwoStepPassword);
        updateRows();
      }
    }
));
    return;
  }
  final String password=firstPassword;
  final TLRPC.TL_account_updatePasswordSettings req=new TLRPC.TL_account_updatePasswordSettings();
  if (currentPasswordHash == null || currentPasswordHash.length == 0) {
    req.password=new TLRPC.TL_inputCheckPasswordEmpty();
  }
  req.new_settings=new TLRPC.TL_account_passwordInputSettings();
  if (clear) {
    UserConfig.getInstance(currentAccount).resetSavedPassword();
    currentSecret=null;
    if (waitingForEmail) {
      req.new_settings.flags=2;
      req.new_settings.email="";
      req.password=new TLRPC.TL_inputCheckPasswordEmpty();
    }
 else {
      req.new_settings.flags=3;
      req.new_settings.hint="";
      req.new_settings.new_password_hash=new byte[0];
      req.new_settings.new_algo=new TLRPC.TL_passwordKdfAlgoUnknown();
      req.new_settings.email="";
    }
  }
 else {
    if (hint == null && currentPassword != null) {
      hint=currentPassword.hint;
    }
    if (hint == null) {
      hint="";
    }
    if (password != null) {
      req.new_settings.flags|=1;
      req.new_settings.hint=hint;
      req.new_settings.new_algo=currentPassword.new_algo;
    }
    if (email.length() > 0) {
      req.new_settings.flags|=2;
      req.new_settings.email=email.trim();
    }
  }
  needShowProgress();
  Utilities.globalQueue.postRunnable(() -> {
    if (req.password == null) {
      req.password=getNewSrpPassword();
    }
    byte[] newPasswordBytes;
    byte[] newPasswordHash;
    if (!clear && password != null) {
      newPasswordBytes=AndroidUtilities.getStringBytes(password);
      if (currentPassword.new_algo instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) {
        TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow algo=(TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow)currentPassword.new_algo;
        newPasswordHash=SRPHelper.getX(newPasswordBytes,algo);
      }
 else {
        newPasswordHash=null;
      }
    }
 else {
      newPasswordBytes=null;
      newPasswordHash=null;
    }
    RequestDelegate requestDelegate=(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      if (error != null && "SRP_ID_INVALID".equals(error.text)) {
        TLRPC.TL_account_getPassword getPasswordReq=new TLRPC.TL_account_getPassword();
        ConnectionsManager.getInstance(currentAccount).sendRequest(getPasswordReq,(response2,error2) -> AndroidUtilities.runOnUIThread(() -> {
          if (error2 == null) {
            currentPassword=(TLRPC.TL_account_password)response2;
            initPasswordNewAlgo(currentPassword);
            setNewPassword(clear);
          }
        }
),ConnectionsManager.RequestFlagWithoutLogin);
        return;
      }
      needHideProgress();
      if (error == null && response instanceof TLRPC.TL_boolTrue) {
        if (clear) {
          currentPassword=null;
          currentPasswordHash=new byte[0];
          loadPasswordInfo(false);
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.didRemoveTwoStepPassword);
          updateRows();
        }
 else {
          if (getParentActivity() == null) {
            return;
          }
          AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
          builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> {
            NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.didSetTwoStepPassword,newPasswordHash,req.new_settings.new_algo,currentPassword.new_secure_algo,currentPassword.secure_random,email,hint,null,firstPassword);
            finishFragment();
          }
);
          if (password == null && currentPassword != null && currentPassword.has_password) {
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
      }
 else       if (error != null) {
        if ("EMAIL_UNCONFIRMED".equals(error.text) || error.text.startsWith("EMAIL_UNCONFIRMED_")) {
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.didSetTwoStepPassword);
          AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
          builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialogInterface,i) -> {
            if (closeAfterSet) {
              TwoStepVerificationActivity activity=new TwoStepVerificationActivity(currentAccount,0);
              activity.setCloseAfterSet(true);
              parentLayout.addFragmentToStack(activity,parentLayout.fragmentsStack.size() - 1);
            }
            NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.didSetTwoStepPassword,newPasswordHash,req.new_settings.new_algo,currentPassword.new_secure_algo,currentPassword.secure_random,email,hint,email,firstPassword);
            finishFragment();
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
          if ("EMAIL_INVALID".equals(error.text)) {
            showAlertWithText(LocaleController.getString("AppName",R.string.AppName),LocaleController.getString("PasswordEmailInvalid",R.string.PasswordEmailInvalid));
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
    }
);
    if (!clear) {
      if (password != null && currentSecret != null && currentSecret.length == 32) {
        if (currentPassword.new_secure_algo instanceof TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000) {
          TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000 newAlgo=(TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000)currentPassword.new_secure_algo;
          byte[] passwordHash=Utilities.computePBKDF2(newPasswordBytes,newAlgo.salt);
          byte[] key=new byte[32];
          System.arraycopy(passwordHash,0,key,0,32);
          byte[] iv=new byte[16];
          System.arraycopy(passwordHash,32,iv,0,16);
          byte[] encryptedSecret=new byte[32];
          System.arraycopy(currentSecret,0,encryptedSecret,0,32);
          Utilities.aesCbcEncryptionByteArraySafe(encryptedSecret,key,iv,0,encryptedSecret.length,0,1);
          req.new_settings.new_secure_settings=new TLRPC.TL_secureSecretSettings();
          req.new_settings.new_secure_settings.secure_algo=newAlgo;
          req.new_settings.new_secure_settings.secure_secret=encryptedSecret;
          req.new_settings.new_secure_settings.secure_secret_id=currentSecretId;
          req.new_settings.flags|=4;
        }
      }
      if (currentPassword.new_algo instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) {
        if (password != null) {
          TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow algo=(TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow)currentPassword.new_algo;
          req.new_settings.new_password_hash=SRPHelper.getVBytes(newPasswordBytes,algo);
          if (req.new_settings.new_password_hash == null) {
            TLRPC.TL_error error=new TLRPC.TL_error();
            error.text="ALGO_INVALID";
            requestDelegate.run(null,error);
          }
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
