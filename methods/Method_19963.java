private void signInWithPhoneAuthCredential(PhoneAuthCredential credential){
  mAuth.signInWithCredential(credential).addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
    @Override public void onComplete(    @NonNull Task<AuthResult> task){
      if (task.isSuccessful()) {
        Log.d(TAG,"signInWithCredential:success");
        FirebaseUser user=task.getResult().getUser();
        updateUI(STATE_SIGNIN_SUCCESS,user);
      }
 else {
        Log.w(TAG,"signInWithCredential:failure",task.getException());
        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
          mVerificationField.setError("Invalid code.");
        }
        updateUI(STATE_SIGNIN_FAILED);
      }
    }
  }
);
}
