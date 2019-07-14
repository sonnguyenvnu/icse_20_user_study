private void startSignIn(){
  Intent intent=AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(Collections.singletonList(new AuthUI.IdpConfig.EmailBuilder().build())).setIsSmartLockEnabled(false).build();
  startActivityForResult(intent,RC_SIGN_IN);
  mViewModel.setIsSigningIn(true);
}
