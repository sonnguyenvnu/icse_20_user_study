/** 
 * @param conf configuration
 * @return authorization instance
 * @since Twitter4J 2.1.11
 */
public static Authorization getInstance(Configuration conf){
  Authorization auth=null;
  String consumerKey=conf.getOAuthConsumerKey();
  String consumerSecret=conf.getOAuthConsumerSecret();
  if (consumerKey != null && consumerSecret != null) {
    if (conf.isApplicationOnlyAuthEnabled()) {
      OAuth2Authorization oauth2=new OAuth2Authorization(conf);
      String tokenType=conf.getOAuth2TokenType();
      String accessToken=conf.getOAuth2AccessToken();
      if (tokenType != null && accessToken != null) {
        oauth2.setOAuth2Token(new OAuth2Token(tokenType,accessToken));
      }
      auth=oauth2;
    }
 else {
      OAuthAuthorization oauth;
      oauth=new OAuthAuthorization(conf);
      String accessToken=conf.getOAuthAccessToken();
      String accessTokenSecret=conf.getOAuthAccessTokenSecret();
      if (accessToken != null && accessTokenSecret != null) {
        oauth.setOAuthAccessToken(new AccessToken(accessToken,accessTokenSecret));
      }
      auth=oauth;
    }
  }
 else {
    String screenName=conf.getUser();
    String password=conf.getPassword();
    if (screenName != null && password != null) {
      auth=new BasicAuthorization(screenName,password);
    }
  }
  if (null == auth) {
    auth=NullAuthorization.getInstance();
  }
  return auth;
}
