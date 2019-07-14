@Override public ParsedToken parseToken(HttpServletRequest request){
  if (request.getRequestURI().contains("oauth2") && request.getParameter(OAuth2Constants.grant_type) != null) {
    return null;
  }
  String accessToken=request.getHeader(OAuth2Constants.authorization);
  if (StringUtils.isEmpty(accessToken)) {
    accessToken=request.getParameter(OAuth2Constants.access_token);
  }
 else {
    String[] arr=accessToken.split("[ ]");
    if (arr.length > 1 && ("Bearer".equalsIgnoreCase(arr[0]) || "OAuth".equalsIgnoreCase(arr[0]))) {
      accessToken=arr[1];
    }
  }
  if (StringUtils.isEmpty(accessToken)) {
    return null;
  }
  OAuth2AccessToken auth2AccessToken=accessTokenService.getTokenByAccessToken(accessToken);
  if (auth2AccessToken == null) {
    throw new GrantTokenException(ErrorType.INVALID_TOKEN);
  }
  Long time=auth2AccessToken.getUpdateTime() != null ? auth2AccessToken.getUpdateTime() : auth2AccessToken.getCreateTime();
  if (System.currentTimeMillis() - time > auth2AccessToken.getExpiresIn() * 1000L) {
    throw new GrantTokenException(ErrorType.EXPIRED_TOKEN);
  }
  return new AuthorizedToken(){
    @Override public String getUserId(){
      return auth2AccessToken.getOwnerId();
    }
    @Override public String getToken(){
      return auth2AccessToken.getAccessToken();
    }
    @Override public String getType(){
      return token_type;
    }
    @Override public long getMaxInactiveInterval(){
      return auth2AccessToken.getExpiresIn() * 1000L;
    }
  }
;
}
