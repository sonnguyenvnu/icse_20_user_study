@Override public void encrypt(@NonNull String keyName,@NonNull String value,@NonNull Callback callback){
  startFingerprintAuthentication(keyName,value,Mode.ENCRYPTION,callback);
}
