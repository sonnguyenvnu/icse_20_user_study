private void updateUI(FirebaseUser user){
  hideProgressDialog();
  TextView idView=findViewById(R.id.anonymousStatusId);
  TextView emailView=findViewById(R.id.anonymousStatusEmail);
  boolean isSignedIn=(user != null);
  if (isSignedIn) {
    idView.setText(getString(R.string.id_fmt,user.getUid()));
    emailView.setText(getString(R.string.email_fmt,user.getEmail()));
  }
 else {
    idView.setText(R.string.signed_out);
    emailView.setText(null);
  }
  findViewById(R.id.buttonAnonymousSignIn).setEnabled(!isSignedIn);
  findViewById(R.id.buttonAnonymousSignOut).setEnabled(isSignedIn);
  findViewById(R.id.buttonLinkAccount).setEnabled(isSignedIn);
}
