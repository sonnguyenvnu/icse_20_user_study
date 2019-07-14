/** 
 * Convenience method for super admin users to remove all tokens (useful for testing, not really in production)
 */
public void clear(){
  accessTokenStore.clear();
  authenticationToAccessTokenStore.clear();
  clientIdToAccessTokenStore.clear();
  refreshTokenStore.clear();
  accessTokenToRefreshTokenStore.clear();
  authenticationStore.clear();
  refreshTokenAuthenticationStore.clear();
  refreshTokenToAccessTokenStore.clear();
  expiryQueue.clear();
}
