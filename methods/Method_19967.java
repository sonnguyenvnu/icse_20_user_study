private void updateUI(int uiState,FirebaseUser user,PhoneAuthCredential cred){
switch (uiState) {
case STATE_INITIALIZED:
    enableViews(mStartButton,mPhoneNumberField);
  disableViews(mVerifyButton,mResendButton,mVerificationField);
mDetailText.setText(null);
break;
case STATE_CODE_SENT:
enableViews(mVerifyButton,mResendButton,mPhoneNumberField,mVerificationField);
disableViews(mStartButton);
mDetailText.setText(R.string.status_code_sent);
break;
case STATE_VERIFY_FAILED:
enableViews(mStartButton,mVerifyButton,mResendButton,mPhoneNumberField,mVerificationField);
mDetailText.setText(R.string.status_verification_failed);
break;
case STATE_VERIFY_SUCCESS:
disableViews(mStartButton,mVerifyButton,mResendButton,mPhoneNumberField,mVerificationField);
mDetailText.setText(R.string.status_verification_succeeded);
if (cred != null) {
if (cred.getSmsCode() != null) {
mVerificationField.setText(cred.getSmsCode());
}
 else {
mVerificationField.setText(R.string.instant_validation);
}
}
break;
case STATE_SIGNIN_FAILED:
mDetailText.setText(R.string.status_sign_in_failed);
break;
case STATE_SIGNIN_SUCCESS:
break;
}
if (user == null) {
mPhoneNumberViews.setVisibility(View.VISIBLE);
mSignedInViews.setVisibility(View.GONE);
mStatusText.setText(R.string.signed_out);
}
 else {
mPhoneNumberViews.setVisibility(View.GONE);
mSignedInViews.setVisibility(View.VISIBLE);
enableViews(mPhoneNumberField,mVerificationField);
mPhoneNumberField.setText(null);
mVerificationField.setText(null);
mStatusText.setText(R.string.signed_in);
mDetailText.setText(getString(R.string.firebase_status_fmt,user.getUid()));
}
}
