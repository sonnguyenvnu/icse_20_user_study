private void updateRows(){
  StringBuilder lastValue=new StringBuilder();
  lastValue.append(setPasswordRow);
  lastValue.append(setPasswordDetailRow);
  lastValue.append(changePasswordRow);
  lastValue.append(turnPasswordOffRow);
  lastValue.append(setRecoveryEmailRow);
  lastValue.append(changeRecoveryEmailRow);
  lastValue.append(resendCodeRow);
  lastValue.append(abortPasswordRow);
  lastValue.append(passwordSetupDetailRow);
  lastValue.append(passwordCodeFieldRow);
  lastValue.append(passwordEnabledDetailRow);
  lastValue.append(shadowRow);
  lastValue.append(rowCount);
  boolean wasCodeField=passwordCodeFieldRow != -1;
  rowCount=0;
  setPasswordRow=-1;
  setPasswordDetailRow=-1;
  changePasswordRow=-1;
  turnPasswordOffRow=-1;
  setRecoveryEmailRow=-1;
  changeRecoveryEmailRow=-1;
  abortPasswordRow=-1;
  resendCodeRow=-1;
  passwordSetupDetailRow=-1;
  passwordCodeFieldRow=-1;
  passwordEnabledDetailRow=-1;
  shadowRow=-1;
  if (!loading && currentPassword != null) {
    if (waitingForEmail) {
      passwordCodeFieldRow=rowCount++;
      passwordSetupDetailRow=rowCount++;
      resendCodeRow=rowCount++;
      abortPasswordRow=rowCount++;
      shadowRow=rowCount++;
    }
 else     if (currentPassword.has_password) {
      changePasswordRow=rowCount++;
      turnPasswordOffRow=rowCount++;
      if (currentPassword.has_recovery) {
        changeRecoveryEmailRow=rowCount++;
      }
 else {
        setRecoveryEmailRow=rowCount++;
      }
      passwordEnabledDetailRow=rowCount++;
    }
 else {
      setPasswordRow=rowCount++;
      setPasswordDetailRow=rowCount++;
    }
  }
  StringBuilder newValue=new StringBuilder();
  newValue.append(setPasswordRow);
  newValue.append(setPasswordDetailRow);
  newValue.append(changePasswordRow);
  newValue.append(turnPasswordOffRow);
  newValue.append(setRecoveryEmailRow);
  newValue.append(changeRecoveryEmailRow);
  newValue.append(resendCodeRow);
  newValue.append(abortPasswordRow);
  newValue.append(passwordSetupDetailRow);
  newValue.append(passwordCodeFieldRow);
  newValue.append(passwordEnabledDetailRow);
  newValue.append(shadowRow);
  newValue.append(rowCount);
  if (listAdapter != null && !lastValue.toString().equals(newValue.toString())) {
    listAdapter.notifyDataSetChanged();
    if (passwordCodeFieldRow == -1 && getParentActivity() != null && wasCodeField) {
      AndroidUtilities.hideKeyboard(getParentActivity().getCurrentFocus());
      codeFieldCell.setText("",false);
    }
  }
  if (fragmentView != null) {
    if (loading || passwordEntered) {
      if (listView != null) {
        listView.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.INVISIBLE);
        listView.setEmptyView(emptyView);
      }
      if (waitingForEmail && currentPassword != null) {
        doneItem.setVisibility(View.VISIBLE);
      }
 else       if (passwordEditText != null) {
        doneItem.setVisibility(View.GONE);
        passwordEditText.setVisibility(View.INVISIBLE);
        titleTextView.setVisibility(View.INVISIBLE);
        bottomTextView.setVisibility(View.INVISIBLE);
        bottomButton.setVisibility(View.INVISIBLE);
      }
      fragmentView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
      fragmentView.setTag(Theme.key_windowBackgroundGray);
    }
 else {
      if (listView != null) {
        listView.setEmptyView(null);
        listView.setVisibility(View.INVISIBLE);
        scrollView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.INVISIBLE);
      }
      if (passwordEditText != null) {
        doneItem.setVisibility(View.VISIBLE);
        passwordEditText.setVisibility(View.VISIBLE);
        fragmentView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        fragmentView.setTag(Theme.key_windowBackgroundWhite);
        titleTextView.setVisibility(View.VISIBLE);
        bottomButton.setVisibility(View.VISIBLE);
        bottomTextView.setVisibility(View.INVISIBLE);
        bottomButton.setText(LocaleController.getString("ForgotPassword",R.string.ForgotPassword));
        if (!TextUtils.isEmpty(currentPassword.hint)) {
          passwordEditText.setHint(currentPassword.hint);
        }
 else {
          passwordEditText.setHint("");
        }
        AndroidUtilities.runOnUIThread(() -> {
          if (!isFinishing() && !destroyed && passwordEditText != null) {
            passwordEditText.requestFocus();
            AndroidUtilities.showKeyboard(passwordEditText);
          }
        }
,200);
      }
    }
  }
}
