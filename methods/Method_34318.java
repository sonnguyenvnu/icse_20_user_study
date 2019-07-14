/** 
 * Check if the current authentication is acting as an authenticated client application not on behalf of a user.
 * @return true if the current authentication represents a client application
 */
public boolean isClient(){
  return OAuth2ExpressionUtils.isOAuthClientAuth(authentication);
}
