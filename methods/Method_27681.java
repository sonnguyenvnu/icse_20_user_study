@OnEditorAction(R.id.twoFactorEditText) public boolean onSend2FA(){
  doLogin();
  return true;
}
