private void updateUI(FirebaseUser user){
  hideProgressDialog();
  if (user != null) {
    mStatusTextView.setText(getString(R.string.msft_status_fmt,user.getDisplayName()));
    mDetailTextView.setText(getString(R.string.firebase_status_fmt,user.getUid()));
    findViewById(R.id.genericSignInButton).setVisibility(View.GONE);
    findViewById(R.id.signOutButton).setVisibility(View.VISIBLE);
  }
 else {
    mStatusTextView.setText(R.string.signed_out);
    mDetailTextView.setText(null);
    findViewById(R.id.genericSignInButton).setVisibility(View.VISIBLE);
    findViewById(R.id.signOutButton).setVisibility(View.GONE);
  }
}
