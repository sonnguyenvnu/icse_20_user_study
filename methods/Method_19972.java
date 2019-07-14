private void signOut(){
  mAuth.signOut();
  TwitterCore.getInstance().getSessionManager().clearActiveSession();
  updateUI(null);
}
