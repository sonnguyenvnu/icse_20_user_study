/** 
 * Create the OAuth token for the specified consumer key.
 * @param authentication The authentication request.
 * @return The OAuth token.
 */
protected OAuthProviderToken createOAuthToken(ConsumerAuthentication authentication){
  return getTokenServices().createUnauthorizedRequestToken(authentication.getConsumerDetails().getConsumerKey(),authentication.getOAuthParameters().get(OAuthConsumerParameter.oauth_callback.toString()));
}
