@Override protected OAuth2AccessToken getAccessToken(ClientDetails client,TokenRequest tokenRequest){
  String refreshToken=tokenRequest.getRequestParameters().get("refresh_token");
  return getTokenServices().refreshAccessToken(refreshToken,tokenRequest);
}
