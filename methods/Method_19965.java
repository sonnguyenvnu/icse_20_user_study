private void updateUI(FirebaseUser user){
  if (user != null) {
    updateUI(STATE_SIGNIN_SUCCESS,user);
  }
 else {
    updateUI(STATE_INITIALIZED);
  }
}
