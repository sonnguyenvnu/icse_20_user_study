private void setCustomToken(String token){
  mCustomToken=token;
  String status;
  if (mCustomToken != null) {
    status="Token:" + mCustomToken;
  }
 else {
    status="Token: null";
  }
  findViewById(R.id.buttonSignIn).setEnabled((mCustomToken != null));
  ((TextView)findViewById(R.id.textTokenStatus)).setText(status);
}
