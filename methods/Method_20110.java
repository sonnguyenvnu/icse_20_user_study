private void signInAnonymously(){
  showProgressDialog(getString(R.string.progress_auth));
  mAuth.signInAnonymously().addOnSuccessListener(this,new OnSuccessListener<AuthResult>(){
    @Override public void onSuccess(    AuthResult authResult){
      Log.d(TAG,"signInAnonymously:SUCCESS");
      hideProgressDialog();
      updateUI(authResult.getUser());
    }
  }
).addOnFailureListener(this,new OnFailureListener(){
    @Override public void onFailure(    @NonNull Exception exception){
      Log.e(TAG,"signInAnonymously:FAILURE",exception);
      hideProgressDialog();
      updateUI(null);
    }
  }
);
}
