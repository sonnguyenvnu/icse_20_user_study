private void signOut(){
  AuthUI.getInstance().signOut(this);
  updateUI(null);
}
