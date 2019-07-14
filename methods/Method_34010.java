protected OAuthProviderToken createOAuthToken(ConsumerAuthentication authentication){
  return getTokenServices().createAccessToken(authentication.getConsumerCredentials().getToken());
}
