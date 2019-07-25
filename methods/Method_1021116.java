@Override public void authenticate(@NonNull Callback callback){
  startFingerprintAuthentication(KEY_AUTH_MODE,"",Mode.AUTHENTICATION,callback);
}
