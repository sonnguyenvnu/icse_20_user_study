private static FingerprintManager.AuthenticationCallback wrapCallback(final AuthenticationCallback callback){
  return new FingerprintManager.AuthenticationCallback(){
    @Override public void onAuthenticationError(    int errMsgId,    CharSequence errString){
      callback.onAuthenticationError(errMsgId,errString);
    }
    @Override public void onAuthenticationHelp(    int helpMsgId,    CharSequence helpString){
      callback.onAuthenticationHelp(helpMsgId,helpString);
    }
    @Override public void onAuthenticationSucceeded(    FingerprintManager.AuthenticationResult result){
      callback.onAuthenticationSucceeded(new AuthenticationResultInternal(unwrapCryptoObject(result.getCryptoObject())));
    }
    @Override public void onAuthenticationFailed(){
      callback.onAuthenticationFailed();
    }
  }
;
}
