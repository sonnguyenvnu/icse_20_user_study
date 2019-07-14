/** 
 * Check if the current authentication is acting on behalf of an authenticated user.
 * @return true if the current authentication represents a user
 */
public boolean isUser(){
  return OAuth2ExpressionUtils.isOAuthUserAuth(authentication);
}
