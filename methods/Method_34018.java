/** 
 * By default, OAuth parameters are adequate if a consumer key is present.
 * @param oauthParams The oauth params.
 * @return Whether the parsed parameters are adequate.
 */
protected boolean parametersAreAdequate(Map<String,String> oauthParams){
  return oauthParams.containsKey(OAuthConsumerParameter.oauth_consumer_key.toString());
}
