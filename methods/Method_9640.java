private void setPasswordSetState(int state){
  if (passwordEditText == null) {
    return;
  }
  passwordSetState=state;
  if (passwordSetState == 0) {
    actionBar.setTitle(LocaleController.getString("YourPassword",R.string.YourPassword));
    if (currentPassword.has_password) {
      titleTextView.setText(LocaleController.getString("PleaseEnterPassword",R.string.PleaseEnterPassword));
    }
 else {
      titleTextView.setText(LocaleController.getString("PleaseEnterFirstPassword",R.string.PleaseEnterFirstPassword));
    }
    passwordEditText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
    bottomTextView.setVisibility(View.INVISIBLE);
    bottomButton.setVisibility(View.INVISIBLE);
  }
 else   if (passwordSetState == 1) {
    actionBar.setTitle(LocaleController.getString("YourPassword",R.string.YourPassword));
    titleTextView.setText(LocaleController.getString("PleaseReEnterPassword",R.string.PleaseReEnterPassword));
    passwordEditText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
    passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
    bottomTextView.setVisibility(View.INVISIBLE);
    bottomButton.setVisibility(View.INVISIBLE);
  }
 else   if (passwordSetState == 2) {
    actionBar.setTitle(LocaleController.getString("PasswordHint",R.string.PasswordHint));
    titleTextView.setText(LocaleController.getString("PasswordHintText",R.string.PasswordHintText));
    passwordEditText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
    passwordEditText.setTransformationMethod(null);
    bottomTextView.setVisibility(View.INVISIBLE);
    bottomButton.setVisibility(View.INVISIBLE);
  }
 else   if (passwordSetState == 3) {
    actionBar.setTitle(LocaleController.getString("RecoveryEmail",R.string.RecoveryEmail));
    titleTextView.setText(LocaleController.getString("YourEmail",R.string.YourEmail));
    passwordEditText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
    passwordEditText.setTransformationMethod(null);
    passwordEditText.setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
    bottomTextView.setVisibility(View.VISIBLE);
    bottomButton.setVisibility(emailOnly ? View.INVISIBLE : View.VISIBLE);
  }
 else   if (passwordSetState == 4) {
    actionBar.setTitle(LocaleController.getString("PasswordRecovery",R.string.PasswordRecovery));
    titleTextView.setText(LocaleController.getString("PasswordCode",R.string.PasswordCode));
    bottomTextView.setText(LocaleController.getString("RestoreEmailSentInfo",R.string.RestoreEmailSentInfo));
    bottomButton.setText(LocaleController.formatString("RestoreEmailTrouble",R.string.RestoreEmailTrouble,currentPassword.email_unconfirmed_pattern != null ? currentPassword.email_unconfirmed_pattern : ""));
    passwordEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
    passwordEditText.setTransformationMethod(null);
    passwordEditText.setInputType(InputType.TYPE_CLASS_PHONE);
    bottomTextView.setVisibility(View.VISIBLE);
    bottomButton.setVisibility(View.VISIBLE);
  }
  passwordEditText.setText("");
}
