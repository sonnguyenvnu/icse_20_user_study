/** 
 * Send an email sign-in link to the specified email.
 */
private void sendSignInLink(String email){
  ActionCodeSettings settings=ActionCodeSettings.newBuilder().setAndroidPackageName(getPackageName(),false,null).setHandleCodeInApp(true).setUrl("https://auth.example.com/emailSignInLink").build();
  hideKeyboard(mEmailField);
  showProgressDialog();
  mAuth.sendSignInLinkToEmail(email,settings).addOnCompleteListener(new OnCompleteListener<Void>(){
    @Override public void onComplete(    @NonNull Task<Void> task){
      hideProgressDialog();
      if (task.isSuccessful()) {
        Log.d(TAG,"Link sent");
        showSnackbar("Sign-in link sent!");
        mPendingEmail=email;
        mStatusText.setText(R.string.status_email_sent);
      }
 else {
        Exception e=task.getException();
        Log.w(TAG,"Could not send link",e);
        showSnackbar("Failed to send link.");
        if (e instanceof FirebaseAuthInvalidCredentialsException) {
          mEmailField.setError("Invalid email address.");
        }
      }
    }
  }
);
}
