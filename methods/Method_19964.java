private void signOut(){
  mAuth.signOut();
  updateUI(STATE_INITIALIZED);
}
