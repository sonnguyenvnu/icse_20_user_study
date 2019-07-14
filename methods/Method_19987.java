private void signUp(){
  Log.d(TAG,"signUp");
  if (!validateForm()) {
    return;
  }
  showProgressDialog();
  String email=mEmailField.getText().toString();
  String password=mPasswordField.getText().toString();
  mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
    @Override public void onComplete(    @NonNull Task<AuthResult> task){
      Log.d(TAG,"createUser:onComplete:" + task.isSuccessful());
      hideProgressDialog();
      if (task.isSuccessful()) {
        onAuthSuccess(task.getResult().getUser());
      }
 else {
        Toast.makeText(SignInActivity.this,"Sign Up Failed",Toast.LENGTH_SHORT).show();
      }
    }
  }
);
}
