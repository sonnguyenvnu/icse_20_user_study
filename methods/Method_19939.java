private void createAccount(String email,String password){
  Log.d(TAG,"createAccount:" + email);
  if (!validateForm()) {
    return;
  }
  showProgressDialog();
  mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
    @Override public void onComplete(    @NonNull Task<AuthResult> task){
      if (task.isSuccessful()) {
        Log.d(TAG,"createUserWithEmail:success");
        FirebaseUser user=mAuth.getCurrentUser();
        updateUI(user);
      }
 else {
        Log.w(TAG,"createUserWithEmail:failure",task.getException());
        Toast.makeText(EmailPasswordActivity.this,"Authentication failed.",Toast.LENGTH_SHORT).show();
        updateUI(null);
      }
      hideProgressDialog();
    }
  }
);
}
