@Override public void onAuthenticateStarted(int requestCode){
  mUsernameLayout.setError(null);
  mPasswordLayout.setError(null);
  updateViews(true);
}
