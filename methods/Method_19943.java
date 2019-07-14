private void handleFacebookAccessToken(AccessToken token){
  Log.d(TAG,"handleFacebookAccessToken:" + token);
  showProgressDialog();
  AuthCredential credential=FacebookAuthProvider.getCredential(token.getToken());
  mAuth.signInWithCredential(credential).addOnCompleteListener(this,new OnCompleteListener<AuthResult>(){
    @Override public void onComplete(    @NonNull Task<AuthResult> task){
      if (task.isSuccessful()) {
        Log.d(TAG,"signInWithCredential:success");
        FirebaseUser user=mAuth.getCurrentUser();
        updateUI(user);
      }
 else {
        Log.w(TAG,"signInWithCredential:failure",task.getException());
        Toast.makeText(FacebookLoginActivity.this,"Authentication failed.",Toast.LENGTH_SHORT).show();
        updateUI(null);
      }
      hideProgressDialog();
    }
  }
);
}
