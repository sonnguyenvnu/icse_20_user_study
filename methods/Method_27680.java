@OnEditorAction(R.id.passwordEditText) public boolean onSendPassword(){
  if (twoFactor.getVisibility() == View.VISIBLE) {
    twoFactorEditText.requestFocus();
  }
 else   if (endpoint.getVisibility() == View.VISIBLE) {
    endpoint.requestFocus();
  }
 else {
    doLogin();
  }
  return true;
}
