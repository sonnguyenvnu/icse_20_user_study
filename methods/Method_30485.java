public ApiAuthenticator getAuthenticator(String authTokenType){
switch (authTokenType) {
case AccountContract.AUTH_TOKEN_TYPE_API_V2:
    return mApiV2Authenticator;
case AccountContract.AUTH_TOKEN_TYPE_FRODO:
  return mFrodoAuthenticator;
default :
throw new IllegalArgumentException("Unknown authTokenType: " + authTokenType);
}
}
