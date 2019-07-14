private void onSignOutClicked(){
  mAuth.signOut();
  updateUI(null);
  mStatusText.setText(R.string.status_email_not_sent);
}
