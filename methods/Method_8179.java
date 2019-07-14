private boolean checkUserName(final String name,boolean alert){
  if (name != null && name.length() > 0) {
    checkTextView.setVisibility(View.VISIBLE);
  }
 else {
    checkTextView.setVisibility(View.GONE);
  }
  if (alert && name.length() == 0) {
    return true;
  }
  if (checkRunnable != null) {
    AndroidUtilities.cancelRunOnUIThread(checkRunnable);
    checkRunnable=null;
    lastCheckName=null;
    if (checkReqId != 0) {
      ConnectionsManager.getInstance(currentAccount).cancelRequest(checkReqId,true);
    }
  }
  lastNameAvailable=false;
  if (name != null) {
    if (name.startsWith("_") || name.endsWith("_")) {
      checkTextView.setText(LocaleController.getString("UsernameInvalid",R.string.UsernameInvalid));
      checkTextView.setTag(Theme.key_windowBackgroundWhiteRedText4);
      checkTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteRedText4));
      return false;
    }
    for (int a=0; a < name.length(); a++) {
      char ch=name.charAt(a);
      if (a == 0 && ch >= '0' && ch <= '9') {
        if (alert) {
          AlertsCreator.showSimpleAlert(this,LocaleController.getString("UsernameInvalidStartNumber",R.string.UsernameInvalidStartNumber));
        }
 else {
          checkTextView.setText(LocaleController.getString("UsernameInvalidStartNumber",R.string.UsernameInvalidStartNumber));
          checkTextView.setTag(Theme.key_windowBackgroundWhiteRedText4);
          checkTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteRedText4));
        }
        return false;
      }
      if (!(ch >= '0' && ch <= '9' || ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z' || ch == '_')) {
        if (alert) {
          AlertsCreator.showSimpleAlert(this,LocaleController.getString("UsernameInvalid",R.string.UsernameInvalid));
        }
 else {
          checkTextView.setText(LocaleController.getString("UsernameInvalid",R.string.UsernameInvalid));
          checkTextView.setTag(Theme.key_windowBackgroundWhiteRedText4);
          checkTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteRedText4));
        }
        return false;
      }
    }
  }
  if (name == null || name.length() < 5) {
    if (alert) {
      AlertsCreator.showSimpleAlert(this,LocaleController.getString("UsernameInvalidShort",R.string.UsernameInvalidShort));
    }
 else {
      checkTextView.setText(LocaleController.getString("UsernameInvalidShort",R.string.UsernameInvalidShort));
      checkTextView.setTag(Theme.key_windowBackgroundWhiteRedText4);
      checkTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteRedText4));
    }
    return false;
  }
  if (name.length() > 32) {
    if (alert) {
      AlertsCreator.showSimpleAlert(this,LocaleController.getString("UsernameInvalidLong",R.string.UsernameInvalidLong));
    }
 else {
      checkTextView.setText(LocaleController.getString("UsernameInvalidLong",R.string.UsernameInvalidLong));
      checkTextView.setTag(Theme.key_windowBackgroundWhiteRedText4);
      checkTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteRedText4));
    }
    return false;
  }
  if (!alert) {
    String currentName=UserConfig.getInstance(currentAccount).getCurrentUser().username;
    if (currentName == null) {
      currentName="";
    }
    if (name.equals(currentName)) {
      checkTextView.setText(LocaleController.formatString("UsernameAvailable",R.string.UsernameAvailable,name));
      checkTextView.setTag(Theme.key_windowBackgroundWhiteGreenText);
      checkTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGreenText));
      return true;
    }
    checkTextView.setText(LocaleController.getString("UsernameChecking",R.string.UsernameChecking));
    checkTextView.setTag(Theme.key_windowBackgroundWhiteGrayText8);
    checkTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText8));
    lastCheckName=name;
    checkRunnable=() -> {
      TLRPC.TL_account_checkUsername req=new TLRPC.TL_account_checkUsername();
      req.username=name;
      checkReqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
        checkReqId=0;
        if (lastCheckName != null && lastCheckName.equals(name)) {
          if (error == null && response instanceof TLRPC.TL_boolTrue) {
            checkTextView.setText(LocaleController.formatString("UsernameAvailable",R.string.UsernameAvailable,name));
            checkTextView.setTag(Theme.key_windowBackgroundWhiteGreenText);
            checkTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGreenText));
            lastNameAvailable=true;
          }
 else {
            checkTextView.setText(LocaleController.getString("UsernameInUse",R.string.UsernameInUse));
            checkTextView.setTag(Theme.key_windowBackgroundWhiteRedText4);
            checkTextView.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteRedText4));
            lastNameAvailable=false;
          }
        }
      }
),ConnectionsManager.RequestFlagFailOnServerErrors);
    }
;
    AndroidUtilities.runOnUIThread(checkRunnable,300);
  }
  return true;
}
