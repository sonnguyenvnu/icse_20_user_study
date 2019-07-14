/** 
 * Logic for handling event firing of a removed token.
 * @param token The token that was removed (possibly null).
 */
protected void onTokenRemoved(OAuthProviderTokenImpl token){
  for (  OAuthTokenLifecycleListener listener : getLifecycleListeners()) {
    listener.tokenExpired(token);
  }
}
