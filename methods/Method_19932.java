private void signInAnonymously(){
  showProgressDialog();
  mAuth.signInAnonymously().addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
    @Override public void onComplete(    @NonNull Task<AuthResult> task){
      if (task.isSuccessful()) {
        Log.d(TAG,"signInAnonymously:success");
        FirebaseUser user=mAuth.getCurrentUser();
        updateUI(user);
      }
 else {
        Log.w(TAG,"signInAnonymously:failure",task.getException());
        Toast.makeText(AnonymousAuthActivity.this,"Authentication failed.",Toast.LENGTH_SHORT).show();
        updateUI(null);
      }
      hideProgressDialog();
    }
  }
);
}
