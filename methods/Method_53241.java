private void init(){
  if (null == auth) {
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
        this.auth=oauth2;
      }
 else {
        OAuthAuthorization oauth=new OAuthAuthorization(conf);
        String accessToken=conf.getOAuthAccessToken();
        String accessTokenSecret=conf.getOAuthAccessTokenSecret();
        if (accessToken != null && accessTokenSecret != null) {
          oauth.setOAuthAccessToken(new AccessToken(accessToken,accessTokenSecret));
        }
        this.auth=oauth;
      }
    }
 else {
      this.auth=NullAuthorization.getInstance();
    }
  }
  http=HttpClientFactory.getInstance(conf.getHttpClientConfiguration());
  setFactory();
}
