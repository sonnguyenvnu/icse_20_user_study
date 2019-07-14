@Override public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result){
  materialDialog.cancel();
  decryptButtonCallbackInterface.confirm(decryptIntent);
}
