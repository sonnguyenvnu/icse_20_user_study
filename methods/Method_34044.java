/** 
 * Whether the auth token is expired.
 * @param authToken The auth token to check for expiration.
 * @return Whether the auth token is expired.
 */
protected boolean isExpired(OAuthProviderTokenImpl authToken){
  if (authToken.isAccessToken()) {
    if ((authToken.getTimestamp() + (getAccessTokenValiditySeconds() * 1000L)) < System.currentTimeMillis()) {
      return true;
    }
  }
 else {
    if ((authToken.getTimestamp() + (getRequestTokenValiditySeconds() * 1000L)) < System.currentTimeMillis()) {
      return true;
    }
  }
  return false;
}
