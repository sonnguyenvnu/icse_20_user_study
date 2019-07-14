private void createAuthenticatorsForActiveAccount(){
  mApiV2Authenticator=new ApiAuthenticator(AccountUtils.getActiveAccount(),AccountContract.AUTH_TOKEN_TYPE_API_V2);
  mFrodoAuthenticator=new ApiAuthenticator(AccountUtils.getActiveAccount(),AccountContract.AUTH_TOKEN_TYPE_FRODO);
}
