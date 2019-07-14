private void updateUI(FirebaseUser user){
  hideProgressDialog();
  if (user != null) {
    mStatusTextView.setText(getString(R.string.twitter_status_fmt,user.getDisplayName()));
    mDetailTextView.setText(getString(R.string.firebase_status_fmt,user.getUid()));
    findViewById(R.id.buttonTwitterLogin).setVisibility(View.GONE);
    findViewById(R.id.buttonTwitterSignout).setVisibility(View.VISIBLE);
  }
 else {
    mStatusTextView.setText(R.string.signed_out);
    mDetailTextView.setText(null);
    findViewById(R.id.buttonTwitterLogin).setVisibility(View.VISIBLE);
    findViewById(R.id.buttonTwitterSignout).setVisibility(View.GONE);
  }
}
