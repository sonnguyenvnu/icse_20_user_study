static public String getTokenFromCode(ButterflyModule module,HttpServletRequest request) throws IOException {
  String redirectUrl=makeRedirectUrl(module,request);
  StringBuffer fullUrlBuf=request.getRequestURL();
  if (request.getQueryString() != null) {
    fullUrlBuf.append('?').append(request.getQueryString());
  }
  AuthorizationCodeResponseUrl authResponse=new AuthorizationCodeResponseUrl(fullUrlBuf.toString());
  if (authResponse.getError() != null) {
  }
 else {
    String code=authResponse.getCode();
    GoogleTokenResponse response=new GoogleAuthorizationCodeTokenRequest(HTTP_TRANSPORT,JSON_FACTORY,CLIENT_ID,CLIENT_SECRET,code,redirectUrl).execute();
    String tokenAndExpiresInSeconds=response.getAccessToken() + "," + response.getExpiresInSeconds();
    return tokenAndExpiresInSeconds;
  }
  return null;
}
