private void updateUI(FirebaseUser user){
  hideProgressDialog();
  if (user != null) {
    mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,user.getEmail(),user.isEmailVerified()));
    mDetailTextView.setText(getString(R.string.firebase_status_fmt,user.getUid()));
    findViewById(R.id.emailPasswordButtons).setVisibility(View.GONE);
    findViewById(R.id.emailPasswordFields).setVisibility(View.GONE);
    findViewById(R.id.signedInButtons).setVisibility(View.VISIBLE);
    findViewById(R.id.verifyEmailButton).setEnabled(!user.isEmailVerified());
  }
 else {
    mStatusTextView.setText(R.string.signed_out);
    mDetailTextView.setText(null);
    findViewById(R.id.emailPasswordButtons).setVisibility(View.VISIBLE);
    findViewById(R.id.emailPasswordFields).setVisibility(View.VISIBLE);
    findViewById(R.id.signedInButtons).setVisibility(View.GONE);
  }
}
