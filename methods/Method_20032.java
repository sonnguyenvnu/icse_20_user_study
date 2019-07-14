private void onSignInClicked(){
  if (FirebaseAuth.getInstance().getCurrentUser() != null) {
    showSnackbar("Signed in.");
    return;
  }
  signIn();
}
