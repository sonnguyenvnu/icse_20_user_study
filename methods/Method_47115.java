private void initLoginDialogViews(View loginDialogView){
  usernameEditText=loginDialogView.findViewById(R.id.edit_text_dialog_ftp_username);
  passwordEditText=loginDialogView.findViewById(R.id.edit_text_dialog_ftp_password);
  usernameTextInput=loginDialogView.findViewById(R.id.text_input_dialog_ftp_username);
  passwordTextInput=loginDialogView.findViewById(R.id.text_input_dialog_ftp_password);
  mAnonymousCheckBox=loginDialogView.findViewById(R.id.checkbox_ftp_anonymous);
  mSecureCheckBox=loginDialogView.findViewById(R.id.checkbox_ftp_secure);
  mAnonymousCheckBox.setOnCheckedChangeListener((buttonView,isChecked) -> {
    if (isChecked) {
      usernameEditText.setEnabled(false);
      passwordEditText.setEnabled(false);
    }
 else {
      usernameEditText.setEnabled(true);
      passwordEditText.setEnabled(true);
    }
  }
);
  if (getUsernameFromPreferences().equals(FtpService.DEFAULT_USERNAME)) {
    mAnonymousCheckBox.setChecked(true);
  }
 else {
    usernameEditText.setText(getUsernameFromPreferences());
    passwordEditText.setText(getPasswordFromPreferences());
  }
  if (getSecurePreference()) {
    mSecureCheckBox.setChecked(true);
  }
 else   mSecureCheckBox.setChecked(false);
}
