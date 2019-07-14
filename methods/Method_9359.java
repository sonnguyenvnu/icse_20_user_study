private void processNext(){
  if (passwordEditText.getText().length() == 0 || currentPasswordType == 0 && passwordEditText.getText().length() != 4) {
    onPasscodeError();
    return;
  }
  if (currentPasswordType == 0) {
    actionBar.setTitle(LocaleController.getString("PasscodePIN",R.string.PasscodePIN));
  }
 else {
    actionBar.setTitle(LocaleController.getString("PasscodePassword",R.string.PasscodePassword));
  }
  dropDownContainer.setVisibility(View.GONE);
  titleTextView.setText(LocaleController.getString("ReEnterYourPasscode",R.string.ReEnterYourPasscode));
  firstPassword=passwordEditText.getText().toString();
  passwordEditText.setText("");
  passcodeSetStep=1;
}
