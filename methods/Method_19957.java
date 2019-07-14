private void onSignInClicked(){
  String email=mEmailField.getText().toString();
  if (TextUtils.isEmpty(email)) {
    mEmailField.setError("Email must not be empty.");
    return;
  }
  signInWithEmailLink(email,mEmailLink);
}
