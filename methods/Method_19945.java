private void updateUI(FirebaseUser user){
  if (user != null) {
    mStatusView.setText(getString(R.string.firebaseui_status_fmt,user.getEmail()));
    mDetailView.setText(getString(R.string.id_fmt,user.getUid()));
    findViewById(R.id.signInButton).setVisibility(View.GONE);
    findViewById(R.id.signOutButton).setVisibility(View.VISIBLE);
  }
 else {
    mStatusView.setText(R.string.signed_out);
    mDetailView.setText(null);
    findViewById(R.id.signInButton).setVisibility(View.VISIBLE);
    findViewById(R.id.signOutButton).setVisibility(View.GONE);
  }
}
