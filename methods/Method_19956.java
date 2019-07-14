private void onSendLinkClicked(){
  String email=mEmailField.getText().toString();
  if (TextUtils.isEmpty(email)) {
    mEmailField.setError("Email must not be empty.");
    return;
  }
  sendSignInLink(email);
}
