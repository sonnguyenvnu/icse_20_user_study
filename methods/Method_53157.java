@Override public AccessToken getOAuthAccessToken(RequestToken requestToken,String oauthVerifier) throws TwitterException {
  this.oauthToken=requestToken;
  return getOAuthAccessToken(oauthVerifier);
}
