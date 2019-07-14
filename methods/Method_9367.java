private void onPasswordDone(final boolean saved){
  final String textPassword;
  if (saved) {
    textPassword=null;
  }
 else {
    textPassword=inputFields[FIELD_PASSWORD].getText().toString();
    if (TextUtils.isEmpty(textPassword)) {
      onPasscodeError(false);
      return;
    }
    showEditDoneProgress(true,true);
  }
  Utilities.globalQueue.postRunnable(() -> {
    TLRPC.TL_account_getPasswordSettings req=new TLRPC.TL_account_getPasswordSettings();
    final byte[] x_bytes;
    if (saved) {
      x_bytes=savedPasswordHash;
    }
 else     if (currentPassword.current_algo instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) {
      byte[] passwordBytes=AndroidUtilities.getStringBytes(textPassword);
      TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow algo=(TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow)currentPassword.current_algo;
      x_bytes=SRPHelper.getX(passwordBytes,algo);
    }
 else {
      x_bytes=null;
    }
    RequestDelegate requestDelegate=new RequestDelegate(){
      private void openRequestInterface(){
        if (inputFields == null) {
          return;
        }
        if (!saved) {
          UserConfig.getInstance(currentAccount).savePassword(x_bytes,saltedPassword);
        }
        AndroidUtilities.hideKeyboard(inputFields[FIELD_PASSWORD]);
        ignoreOnFailure=true;
        int type;
        if (currentBotId == 0) {
          type=TYPE_MANAGE;
        }
 else {
          type=TYPE_REQUEST;
        }
        PassportActivity activity=new PassportActivity(type,currentBotId,currentScope,currentPublicKey,currentPayload,currentNonce,currentCallbackUrl,currentForm,currentPassword);
        activity.currentEmail=currentEmail;
        activity.currentAccount=currentAccount;
        activity.saltedPassword=saltedPassword;
        activity.secureSecret=secureSecret;
        activity.secureSecretId=secureSecretId;
        activity.needActivityResult=needActivityResult;
        if (parentLayout == null || !parentLayout.checkTransitionAnimation()) {
          presentFragment(activity,true);
        }
 else {
          presentAfterAnimation=activity;
        }
      }
      private void resetSecret(){
        TLRPC.TL_account_updatePasswordSettings req2=new TLRPC.TL_account_updatePasswordSettings();
        if (currentPassword.current_algo instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) {
          TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow algo=(TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow)currentPassword.current_algo;
          req2.password=SRPHelper.startCheck(x_bytes,currentPassword.srp_id,currentPassword.srp_B,algo);
        }
        req2.new_settings=new TLRPC.TL_account_passwordInputSettings();
        req2.new_settings.new_secure_settings=new TLRPC.TL_secureSecretSettings();
        req2.new_settings.new_secure_settings.secure_secret=new byte[0];
        req2.new_settings.new_secure_settings.secure_algo=new TLRPC.TL_securePasswordKdfAlgoUnknown();
        req2.new_settings.new_secure_settings.secure_secret_id=0;
        req2.new_settings.flags|=4;
        ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
          if (error != null && "SRP_ID_INVALID".equals(error.text)) {
            TLRPC.TL_account_getPassword getPasswordReq=new TLRPC.TL_account_getPassword();
            ConnectionsManager.getInstance(currentAccount).sendRequest(getPasswordReq,(response2,error2) -> AndroidUtilities.runOnUIThread(() -> {
              if (error2 == null) {
                currentPassword=(TLRPC.TL_account_password)response2;
                TwoStepVerificationActivity.initPasswordNewAlgo(currentPassword);
                resetSecret();
              }
            }
),ConnectionsManager.RequestFlagWithoutLogin);
            return;
          }
          generateNewSecret();
        }
));
      }
      private void generateNewSecret(){
        Utilities.globalQueue.postRunnable(() -> {
          Utilities.random.setSeed(currentPassword.secure_random);
          TLRPC.TL_account_updatePasswordSettings req1=new TLRPC.TL_account_updatePasswordSettings();
          if (currentPassword.current_algo instanceof TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow) {
            TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow algo=(TLRPC.TL_passwordKdfAlgoSHA256SHA256PBKDF2HMACSHA512iter100000SHA256ModPow)currentPassword.current_algo;
            req1.password=SRPHelper.startCheck(x_bytes,currentPassword.srp_id,currentPassword.srp_B,algo);
          }
          req1.new_settings=new TLRPC.TL_account_passwordInputSettings();
          secureSecret=getRandomSecret();
          secureSecretId=Utilities.bytesToLong(Utilities.computeSHA256(secureSecret));
          if (currentPassword.new_secure_algo instanceof TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000) {
            TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000 newAlgo=(TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000)currentPassword.new_secure_algo;
            saltedPassword=Utilities.computePBKDF2(AndroidUtilities.getStringBytes(textPassword),newAlgo.salt);
            byte[] key=new byte[32];
            System.arraycopy(saltedPassword,0,key,0,32);
            byte[] iv=new byte[16];
            System.arraycopy(saltedPassword,32,iv,0,16);
            Utilities.aesCbcEncryptionByteArraySafe(secureSecret,key,iv,0,secureSecret.length,0,1);
            req1.new_settings.new_secure_settings=new TLRPC.TL_secureSecretSettings();
            req1.new_settings.new_secure_settings.secure_algo=newAlgo;
            req1.new_settings.new_secure_settings.secure_secret=secureSecret;
            req1.new_settings.new_secure_settings.secure_secret_id=secureSecretId;
            req1.new_settings.flags|=4;
          }
          ConnectionsManager.getInstance(currentAccount).sendRequest(req1,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
            if (error != null && "SRP_ID_INVALID".equals(error.text)) {
              TLRPC.TL_account_getPassword getPasswordReq=new TLRPC.TL_account_getPassword();
              ConnectionsManager.getInstance(currentAccount).sendRequest(getPasswordReq,(response2,error2) -> AndroidUtilities.runOnUIThread(() -> {
                if (error2 == null) {
                  currentPassword=(TLRPC.TL_account_password)response2;
                  TwoStepVerificationActivity.initPasswordNewAlgo(currentPassword);
                  generateNewSecret();
                }
              }
),ConnectionsManager.RequestFlagWithoutLogin);
              return;
            }
            if (currentForm == null) {
              currentForm=new TLRPC.TL_account_authorizationForm();
            }
            openRequestInterface();
          }
));
        }
);
      }
      @Override public void run(      final TLObject response,      final TLRPC.TL_error error){
        if (error != null && "SRP_ID_INVALID".equals(error.text)) {
          TLRPC.TL_account_getPassword getPasswordReq=new TLRPC.TL_account_getPassword();
          ConnectionsManager.getInstance(currentAccount).sendRequest(getPasswordReq,(response2,error2) -> AndroidUtilities.runOnUIThread(() -> {
            if (error2 == null) {
              currentPassword=(TLRPC.TL_account_password)response2;
              TwoStepVerificationActivity.initPasswordNewAlgo(currentPassword);
              onPasswordDone(saved);
            }
          }
),ConnectionsManager.RequestFlagWithoutLogin);
          return;
        }
        if (error == null) {
          Utilities.globalQueue.postRunnable(() -> {
            TLRPC.TL_account_passwordSettings settings=(TLRPC.TL_account_passwordSettings)response;
            byte[] secure_salt;
            if (settings.secure_settings != null) {
              secureSecret=settings.secure_settings.secure_secret;
              secureSecretId=settings.secure_settings.secure_secret_id;
              if (settings.secure_settings.secure_algo instanceof TLRPC.TL_securePasswordKdfAlgoSHA512) {
                TLRPC.TL_securePasswordKdfAlgoSHA512 algo=(TLRPC.TL_securePasswordKdfAlgoSHA512)settings.secure_settings.secure_algo;
                secure_salt=algo.salt;
                saltedPassword=Utilities.computeSHA512(secure_salt,AndroidUtilities.getStringBytes(textPassword),secure_salt);
              }
 else               if (settings.secure_settings.secure_algo instanceof TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000) {
                TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000 algo=(TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000)settings.secure_settings.secure_algo;
                secure_salt=algo.salt;
                saltedPassword=Utilities.computePBKDF2(AndroidUtilities.getStringBytes(textPassword),algo.salt);
              }
 else               if (settings.secure_settings.secure_algo instanceof TLRPC.TL_securePasswordKdfAlgoUnknown) {
                AndroidUtilities.runOnUIThread(() -> AlertsCreator.showUpdateAppAlert(getParentActivity(),LocaleController.getString("UpdateAppAlert",R.string.UpdateAppAlert),true));
                return;
              }
 else {
                secure_salt=new byte[0];
              }
            }
 else {
              if (currentPassword.new_secure_algo instanceof TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000) {
                TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000 algo=(TLRPC.TL_securePasswordKdfAlgoPBKDF2HMACSHA512iter100000)currentPassword.new_secure_algo;
                secure_salt=algo.salt;
                saltedPassword=Utilities.computePBKDF2(AndroidUtilities.getStringBytes(textPassword),algo.salt);
              }
 else {
                secure_salt=new byte[0];
              }
              secureSecret=null;
              secureSecretId=0;
            }
            AndroidUtilities.runOnUIThread(() -> {
              currentEmail=settings.email;
              if (saved) {
                saltedPassword=savedSaltedPassword;
              }
              if (!checkSecret(decryptSecret(secureSecret,saltedPassword),secureSecretId) || secure_salt.length == 0 || secureSecretId == 0) {
                if (saved) {
                  UserConfig.getInstance(currentAccount).resetSavedPassword();
                  usingSavedPassword=0;
                  updatePasswordInterface();
                }
 else {
                  if (currentForm != null) {
                    currentForm.values.clear();
                    currentForm.errors.clear();
                  }
                  if (secureSecret == null || secureSecret.length == 0) {
                    generateNewSecret();
                  }
 else {
                    resetSecret();
                  }
                }
              }
 else               if (currentBotId == 0) {
                TLRPC.TL_account_getAllSecureValues req12=new TLRPC.TL_account_getAllSecureValues();
                ConnectionsManager.getInstance(currentAccount).sendRequest(req12,(response1,error1) -> AndroidUtilities.runOnUIThread(() -> {
                  if (response1 != null) {
                    currentForm=new TLRPC.TL_account_authorizationForm();
                    TLRPC.Vector vector=(TLRPC.Vector)response1;
                    for (int a=0, size=vector.objects.size(); a < size; a++) {
                      currentForm.values.add((TLRPC.TL_secureValue)vector.objects.get(a));
                    }
                    openRequestInterface();
                  }
 else {
                    if ("APP_VERSION_OUTDATED".equals(error1.text)) {
                      AlertsCreator.showUpdateAppAlert(getParentActivity(),LocaleController.getString("UpdateAppAlert",R.string.UpdateAppAlert),true);
                    }
 else {
                      showAlertWithText(LocaleController.getString("AppName",R.string.AppName),error1.text);
                    }
                    showEditDoneProgress(true,false);
                  }
                }
));
              }
 else {
                openRequestInterface();
              }
            }
);
          }
);
        }
 else {
          AndroidUtilities.runOnUIThread(() -> {
            if (saved) {
              UserConfig.getInstance(currentAccount).resetSavedPassword();
              usingSavedPassword=0;
              updatePasswordInterface();
              if (inputFieldContainers != null && inputFieldContainers[FIELD_PASSWORD].getVisibility() == View.VISIBLE) {
                inputFields[FIELD_PASSWORD].requestFocus();
                AndroidUtilities.showKeyboard(inputFields[FIELD_PASSWORD]);
              }
            }
 else {
              showEditDoneProgress(true,false);
              if (error.text.equals("PASSWORD_HASH_INVALID")) {
                onPasscodeError(true);
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
);
        }
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
      int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,requestDelegate,ConnectionsManager.RequestFlagFailOnServerErrors | ConnectionsManager.RequestFlagWithoutLogin);
      ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
    }
 else {
      TLRPC.TL_error error=new TLRPC.TL_error();
      error.text="PASSWORD_HASH_INVALID";
      requestDelegate.run(null,error);
    }
  }
);
}
