private void updateUI(FirebaseUser user){
  hideProgressDialog();
  if (user != null) {
    mStatusTextView.setText(getString(R.string.facebook_status_fmt,user.getDisplayName()));
    mDetailTextView.setText(getString(R.string.firebase_status_fmt,user.getUid()));
    findViewById(R.id.buttonFacebookLogin).setVisibility(View.GONE);
    findViewById(R.id.buttonFacebookSignout).setVisibility(View.VISIBLE);
  }
 else {
    mStatusTextView.setText(R.string.signed_out);
    mDetailTextView.setText(null);
    findViewById(R.id.buttonFacebookLogin).setVisibility(View.VISIBLE);
    findViewById(R.id.buttonFacebookSignout).setVisibility(View.GONE);
  }
}
