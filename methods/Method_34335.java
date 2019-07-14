protected OAuth2Authentication getOAuth2Authentication(ClientDetails client,TokenRequest tokenRequest){
  OAuth2Request storedOAuth2Request=requestFactory.createOAuth2Request(client,tokenRequest);
  return new OAuth2Authentication(storedOAuth2Request,null);
}
