/** 
 * Deny access to oauth requests, so used for example to only allow web UI users to access a resource.
 * @return true if the current authentication is not an OAuth2 type
 */
public boolean denyOAuthClient(){
  return !OAuth2ExpressionUtils.isOAuth(authentication);
}
