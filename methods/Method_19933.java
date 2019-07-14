private void linkAccount(){
  if (!validateLinkForm()) {
    return;
  }
  String email=mEmailField.getText().toString();
  String password=mPasswordField.getText().toString();
  AuthCredential credential=EmailAuthProvider.getCredential(email,password);
  showProgressDialog();
  mAuth.getCurrentUser().linkWithCredential(credential).addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
    @Override public void onComplete(    @NonNull Task<AuthResult> task){
      if (task.isSuccessful()) {
        Log.d(TAG,"linkWithCredential:success");
        FirebaseUser user=task.getResult().getUser();
        updateUI(user);
      }
 else {
        Log.w(TAG,"linkWithCredential:failure",task.getException());
        Toast.makeText(AnonymousAuthActivity.this,"Authentication failed.",Toast.LENGTH_SHORT).show();
        updateUI(null);
      }
      hideProgressDialog();
    }
  }
);
}
