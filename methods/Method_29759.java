private void authenticate(){
  String username=mUsernameEdit.getText().toString();
  String password=mPasswordEdit.getText().toString();
  boolean cancel=false;
  View errorView=null;
  if (TextUtils.isEmpty(username)) {
    mUsernameLayout.setError(getString(R.string.auth_error_empty_username));
    errorView=mUsernameEdit;
    cancel=true;
  }
  if (TextUtils.isEmpty(password)) {
    mPasswordLayout.setError(getString(R.string.auth_error_empty_password));
    if (errorView == null) {
      errorView=mPasswordEdit;
    }
    cancel=true;
  }
  if (cancel) {
    errorView.requestFocus();
  }
 else {
    mAuthenticateRequest.start(AUTH_TOKEN_TYPE,username,password);
  }
}
