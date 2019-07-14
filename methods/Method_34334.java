protected OAuth2AccessToken getAccessToken(ClientDetails client,TokenRequest tokenRequest){
  return tokenServices.createAccessToken(getOAuth2Authentication(client,tokenRequest));
}
