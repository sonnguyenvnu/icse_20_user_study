private void signIn(String email,String password){
  Log.d(TAG,"signIn:" + email);
  if (!validateForm()) {
    return;
  }
  showProgressDialog();
  mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
    @Override public void onComplete(    @NonNull Task<AuthResult> task){
      if (task.isSuccessful()) {
        Log.d(TAG,"signInWithEmail:success");
        FirebaseUser user=mAuth.getCurrentUser();
        updateUI(user);
      }
 else {
        Log.w(TAG,"signInWithEmail:failure",task.getException());
        Toast.makeText(EmailPasswordActivity.this,"Authentication failed.",Toast.LENGTH_SHORT).show();
        updateUI(null);
      }
      if (!task.isSuccessful()) {
        mStatusTextView.setText(R.string.auth_failed);
      }
      hideProgressDialog();
    }
  }
);
}
