private void verifyPhoneNumberWithCode(String verificationId,String code){
  PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationId,code);
  signInWithPhoneAuthCredential(credential);
}
