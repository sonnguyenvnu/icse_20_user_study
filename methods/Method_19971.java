private void handleTwitterSession(TwitterSession session){
  Log.d(TAG,"handleTwitterSession:" + session);
  showProgressDialog();
  AuthCredential credential=TwitterAuthProvider.getCredential(session.getAuthToken().token,session.getAuthToken().secret);
  mAuth.signInWithCredential(credential).addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
    @Override public void onComplete(    @NonNull Task<AuthResult> task){
      if (task.isSuccessful()) {
        Log.d(TAG,"signInWithCredential:success");
        FirebaseUser user=mAuth.getCurrentUser();
        updateUI(user);
      }
 else {
        Log.w(TAG,"signInWithCredential:failure",task.getException());
        Toast.makeText(TwitterLoginActivity.this,"Authentication failed.",Toast.LENGTH_SHORT).show();
        updateUI(null);
      }
      hideProgressDialog();
    }
  }
);
}
