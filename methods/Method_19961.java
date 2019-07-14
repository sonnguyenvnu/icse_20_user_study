private void startPhoneNumberVerification(String phoneNumber){
  PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber,60,TimeUnit.SECONDS,this,mCallbacks);
  mVerificationInProgress=true;
}
