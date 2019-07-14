/** 
 * Flag to determine whether a request that has an existing access token, and which then leads to an AccessTokenRequiredException should be retried (immediately, once). Useful if the remote server doesn't recognize an old token which is stored in the client, but is happy to re-grant it.
 * @param retryBadAccessTokens the flag to set (default true)
 */
public void setRetryBadAccessTokens(boolean retryBadAccessTokens){
  this.retryBadAccessTokens=retryBadAccessTokens;
}
