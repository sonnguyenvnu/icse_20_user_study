/** 
 * Sign in using an email address and a link, the link is passed to the Activity from the dynamic link contained in the email.
 */
private void signInWithEmailLink(String email,String link){
  Log.d(TAG,"signInWithLink:" + link);
  hideKeyboard(mEmailField);
  showProgressDialog();
  mAuth.signInWithEmailLink(email,link).addOnCompleteListener(new OnCompleteListener<AuthResult>(){
    @Override public void onComplete(    @NonNull Task<AuthResult> task){
      hideProgressDialog();
      mPendingEmail=null;
      if (task.isSuccessful()) {
        Log.d(TAG,"signInWithEmailLink:success");
        mEmailField.setText(null);
        updateUI(task.getResult().getUser());
      }
 else {
        Log.w(TAG,"signInWithEmailLink:failure",task.getException());
        updateUI(null);
        if (task.getException() instanceof FirebaseAuthActionCodeException) {
          showSnackbar("Invalid or expired sign-in link.");
        }
      }
    }
  }
);
}
