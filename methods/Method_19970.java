@Override public void onClick(View view){
switch (view.getId()) {
case R.id.buttonStartVerification:
    if (!validatePhoneNumber()) {
      return;
    }
  startPhoneNumberVerification(mPhoneNumberField.getText().toString());
break;
case R.id.buttonVerifyPhone:
String code=mVerificationField.getText().toString();
if (TextUtils.isEmpty(code)) {
mVerificationField.setError("Cannot be empty.");
return;
}
verifyPhoneNumberWithCode(mVerificationId,code);
break;
case R.id.buttonResend:
resendVerificationCode(mPhoneNumberField.getText().toString(),mResendToken);
break;
case R.id.signOutButton:
signOut();
break;
}
}
