/** 
 * Logic for handling event firing of a created token.
 * @param token The token that was created.
 */
protected void onTokenCreated(OAuthProviderTokenImpl token){
  for (  OAuthTokenLifecycleListener listener : getLifecycleListeners()) {
    listener.tokenCreated(token);
  }
}
