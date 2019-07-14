private void updateUI(FirebaseUser user){
  if (user != null) {
    ((TextView)findViewById(R.id.textSignInStatus)).setText("User ID: " + user.getUid());
  }
 else {
    ((TextView)findViewById(R.id.textSignInStatus)).setText("Error: sign in failed.");
  }
}
