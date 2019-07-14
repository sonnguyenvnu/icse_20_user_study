@Override public void onStart(){
  super.onStart();
  FirebaseUser currentUser=mAuth.getCurrentUser();
  updateUI(currentUser);
  Task<AuthResult> pending=mAuth.getPendingAuthResult();
  if (pending != null) {
    pending.addOnSuccessListener(new OnSuccessListener<AuthResult>(){
      @Override public void onSuccess(      AuthResult authResult){
        Log.d(TAG,"checkPending:onSuccess:" + authResult);
      }
    }
).addOnFailureListener(new OnFailureListener(){
      @Override public void onFailure(      @NonNull Exception e){
        Log.w(TAG,"checkPending:onFailure",e);
      }
    }
);
  }
 else {
    Log.d(TAG,"pending: null");
  }
}
