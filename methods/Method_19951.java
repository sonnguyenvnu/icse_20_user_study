private void updateUI(FirebaseUser user){
  hideProgressDialog();
  if (user != null) {
    mStatusTextView.setText(getString(R.string.google_status_fmt,user.getEmail()));
    mDetailTextView.setText(getString(R.string.firebase_status_fmt,user.getUid()));
    findViewById(R.id.signInButton).setVisibility(View.GONE);
    findViewById(R.id.signOutAndDisconnect).setVisibility(View.VISIBLE);
  }
 else {
    mStatusTextView.setText(R.string.signed_out);
    mDetailTextView.setText(null);
    findViewById(R.id.signInButton).setVisibility(View.VISIBLE);
    findViewById(R.id.signOutAndDisconnect).setVisibility(View.GONE);
  }
}
