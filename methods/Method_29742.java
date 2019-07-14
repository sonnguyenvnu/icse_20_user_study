@Override public Bundle getAuthToken(AccountAuthenticatorResponse response,Account account,String authTokenType,Bundle options){
  if (!MoreTextUtils.equalsAny(authTokenType,AccountContract.AUTH_TOKEN_TYPE_API_V2,AccountContract.AUTH_TOKEN_TYPE_FRODO)) {
    return makeErrorBundle(AccountManager.ERROR_CODE_BAD_ARGUMENTS,"invalid authTokenType:" + authTokenType);
  }
  String authToken=AccountUtils.peekAuthToken(account,authTokenType);
  if (TextUtils.isEmpty(authToken)) {
    String refreshToken=AccountUtils.getRefreshToken(account,authTokenType);
    if (!TextUtils.isEmpty(refreshToken)) {
      try {
        AuthenticationResponse authenticationResponse=ApiService.getInstance().authenticate(authTokenType,refreshToken).execute();
        authToken=authenticationResponse.accessToken;
        AccountUtils.setUserName(account,authenticationResponse.userName);
        AccountUtils.setUserId(account,authenticationResponse.userId);
        AccountUtils.setRefreshToken(account,authTokenType,authenticationResponse.refreshToken);
      }
 catch (      ApiError e) {
        LogUtils.e(e.toString());
      }
    }
  }
  if (TextUtils.isEmpty(authToken)) {
    String password=AccountUtils.getPassword(account);
    if (password == null) {
      return makeErrorBundle(AccountManager.ERROR_CODE_BAD_AUTHENTICATION,"AccountManager.getPassword() returned null");
    }
    ApiService apiService=ApiService.getInstance();
    try {
      AuthenticationResponse authenticationResponse=apiService.authenticate(authTokenType,account.name,password).execute();
      authToken=authenticationResponse.accessToken;
      AccountUtils.setUserName(account,authenticationResponse.userName);
      AccountUtils.setUserId(account,authenticationResponse.userId);
      AccountUtils.setRefreshToken(account,authTokenType,authenticationResponse.refreshToken);
    }
 catch (    ApiError e) {
      LogUtils.e(e.toString());
      if (e.bodyJson != null && e.code != Codes.Custom.INVALID_ERROR_RESPONSE) {
        String errorString=ApiError.getErrorString(e,mContext);
switch (e.code) {
case Codes.Token.INVALID_APIKEY:
case Codes.Token.APIKEY_IS_BLOCKED:
case Codes.Token.INVALID_REQUEST_URI:
case Codes.Token.INVALID_CREDENCIAL2:
case Codes.Token.REQUIRED_PARAMETER_IS_MISSING:
case Codes.Token.CLIENT_SECRET_MISMATCH:
          ApiService.getInstance().cancelApiRequests();
        return makeFailureIntentBundle(AuthenticatorUtils.makeSetApiKeyIntent(mContext),errorString);
case Codes.Token.USERNAME_PASSWORD_MISMATCH:
      ApiService.getInstance().cancelApiRequests();
    return makeFailureIntentBundle(makeUpdateCredentialIntent(response,account),errorString);
case Codes.Token.INVALID_USER:
case Codes.Token.USER_HAS_BLOCKED:
case Codes.Token.USER_LOCKED:
  ApiService.getInstance().cancelApiRequests();
return makeFailureIntentBundle(AuthenticatorUtils.makeWebsiteIntent(mContext),errorString);
}
return makeErrorBundle(AccountManager.ERROR_CODE_BAD_AUTHENTICATION,errorString);
}
 else if (e.response != null) {
return makeErrorBundle(AccountManager.ERROR_CODE_INVALID_RESPONSE,e);
}
 else {
return makeErrorBundle(AccountManager.ERROR_CODE_NETWORK_ERROR,e);
}
}
}
if (TextUtils.isEmpty(authToken)) {
return makeErrorBundle(AccountManager.ERROR_CODE_INVALID_RESPONSE,"authToken is still null");
}
Bundle result=makeSuccessBundle(account.name);
result.putString(AccountManager.KEY_AUTHTOKEN,authToken);
return result;
}
