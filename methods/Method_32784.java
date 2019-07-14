@ReactMethod public void isPinOrFingerprintSet(Callback callback){
  KeyguardManager keyguardManager=(KeyguardManager)this.reactContext.getApplicationContext().getSystemService(Context.KEYGUARD_SERVICE);
  callback.invoke(keyguardManager.isKeyguardSecure());
}
