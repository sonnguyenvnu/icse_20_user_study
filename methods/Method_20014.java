private boolean shouldStartSignIn(){
  return (!mViewModel.getIsSigningIn() && FirebaseAuth.getInstance().getCurrentUser() == null);
}
