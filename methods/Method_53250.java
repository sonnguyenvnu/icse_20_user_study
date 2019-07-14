@Override public synchronized AccessToken getOAuthAccessToken(RequestToken requestToken) throws TwitterException {
  OAuthSupport oauth=getOAuth();
  AccessToken oauthAccessToken=oauth.getOAuthAccessToken(requestToken);
  screenName=oauthAccessToken.getScreenName();
  return oauthAccessToken;
}
