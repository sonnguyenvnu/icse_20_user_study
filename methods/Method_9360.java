private void processDone(){
  if (passwordEditText.getText().length() == 0) {
    onPasscodeError();
    return;
  }
  if (type == 1) {
    if (!firstPassword.equals(passwordEditText.getText().toString())) {
      try {
        Toast.makeText(getParentActivity(),LocaleController.getString("PasscodeDoNotMatch",R.string.PasscodeDoNotMatch),Toast.LENGTH_SHORT).show();
      }
 catch (      Exception e) {
        FileLog.e(e);
      }
      AndroidUtilities.shakeView(titleTextView,2,0);
      passwordEditText.setText("");
      return;
    }
    try {
      SharedConfig.passcodeSalt=new byte[16];
      Utilities.random.nextBytes(SharedConfig.passcodeSalt);
      byte[] passcodeBytes=firstPassword.getBytes("UTF-8");
      byte[] bytes=new byte[32 + passcodeBytes.length];
      System.arraycopy(SharedConfig.passcodeSalt,0,bytes,0,16);
      System.arraycopy(passcodeBytes,0,bytes,16,passcodeBytes.length);
      System.arraycopy(SharedConfig.passcodeSalt,0,bytes,passcodeBytes.length + 16,16);
      SharedConfig.passcodeHash=Utilities.bytesToHex(Utilities.computeSHA256(bytes,0,bytes.length));
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    SharedConfig.allowScreenCapture=true;
    SharedConfig.passcodeType=currentPasswordType;
    SharedConfig.saveConfig();
    finishFragment();
    NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.didSetPasscode);
    passwordEditText.clearFocus();
    AndroidUtilities.hideKeyboard(passwordEditText);
  }
 else   if (type == 2) {
    if (SharedConfig.passcodeRetryInMs > 0) {
      int value=Math.max(1,(int)Math.ceil(SharedConfig.passcodeRetryInMs / 1000.0));
      Toast.makeText(getParentActivity(),LocaleController.formatString("TooManyTries",R.string.TooManyTries,LocaleController.formatPluralString("Seconds",value)),Toast.LENGTH_SHORT).show();
      passwordEditText.setText("");
      onPasscodeError();
      return;
    }
    if (!SharedConfig.checkPasscode(passwordEditText.getText().toString())) {
      SharedConfig.increaseBadPasscodeTries();
      passwordEditText.setText("");
      onPasscodeError();
      return;
    }
    SharedConfig.badPasscodeTries=0;
    SharedConfig.saveConfig();
    passwordEditText.clearFocus();
    AndroidUtilities.hideKeyboard(passwordEditText);
    presentFragment(new PasscodeActivity(0),true);
  }
}
