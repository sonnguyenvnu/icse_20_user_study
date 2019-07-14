private void updatePasscodeButton(){
  if (passcodeItem == null) {
    return;
  }
  if (SharedConfig.passcodeHash.length() != 0 && !searching) {
    passcodeItem.setVisibility(View.VISIBLE);
    if (SharedConfig.appLocked) {
      passcodeItem.setIcon(R.drawable.lock_close);
      passcodeItem.setContentDescription(LocaleController.getString("AccDescrPasscodeUnlock",R.string.AccDescrPasscodeUnlock));
    }
 else {
      passcodeItem.setIcon(R.drawable.lock_open);
      passcodeItem.setContentDescription(LocaleController.getString("AccDescrPasscodeLock",R.string.AccDescrPasscodeLock));
    }
  }
 else {
    passcodeItem.setVisibility(View.GONE);
  }
}
