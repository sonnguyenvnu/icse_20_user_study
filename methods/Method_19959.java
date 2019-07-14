private void updateUI(@Nullable FirebaseUser user){
  if (user != null) {
    mStatusText.setText(getString(R.string.passwordless_status_fmt,user.getEmail(),user.isEmailVerified()));
    findViewById(R.id.passwordlessFields).setVisibility(View.GONE);
    findViewById(R.id.passwordlessButtons).setVisibility(View.GONE);
    findViewById(R.id.signedInButtons).setVisibility(View.VISIBLE);
  }
 else {
    findViewById(R.id.passwordlessFields).setVisibility(View.VISIBLE);
    findViewById(R.id.passwordlessButtons).setVisibility(View.VISIBLE);
    findViewById(R.id.signedInButtons).setVisibility(View.GONE);
  }
}
