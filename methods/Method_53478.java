/** 
 * Returns a OAuth Authenticated instance.<br> consumer key and consumer Secret must be provided by twitter4j.properties, or system properties. Unlike  {@link TwitterStream#setOAuthAccessToken(twitter4j.auth.AccessToken)}, this factory method potentially returns a cached instance.
 * @param accessToken access token
 * @return an instance
 */
public TwitterStream getInstance(AccessToken accessToken){
  String consumerKey=conf.getOAuthConsumerKey();
  String consumerSecret=conf.getOAuthConsumerSecret();
  if (null == consumerKey && null == consumerSecret) {
    throw new IllegalStateException("Consumer key and Consumer secret not supplied.");
  }
  OAuthAuthorization oauth=new OAuthAuthorization(conf);
  oauth.setOAuthAccessToken(accessToken);
  return getInstance(conf,oauth);
}
