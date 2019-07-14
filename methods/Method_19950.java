private void firebaseAuthWithGoogle(GoogleSignInAccount acct){
  Log.d(TAG,"firebaseAuthWithGoogle:" + acct.getId());
  showProgressDialog();
  AuthCredential credential=GoogleAuthProvider.getCredential(acct.getIdToken(),null);
  mAuth.signInWithCredential(credential).addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
    @Override public void onComplete(    @NonNull Task<AuthResult> task){
      if (task.isSuccessful()) {
        Log.d(TAG,"signInWithCredential:success");
        FirebaseUser user=mAuth.getCurrentUser();
        updateUI(user);
      }
 else {
        Log.w(TAG,"signInWithCredential:failure",task.getException());
        Snackbar.make(findViewById(R.id.main_layout),"Authentication Failed.",Snackbar.LENGTH_SHORT).show();
        updateUI(null);
      }
      hideProgressDialog();
    }
  }
);
}
