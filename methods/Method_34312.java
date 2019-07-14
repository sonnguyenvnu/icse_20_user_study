/** 
 * Check if the OAuth2 client (not the user) has one of the roles specified. To check the user's roles see {@link #clientHasAnyRole(String)}.
 * @param roles the roles to check
 * @return true if the OAuth2 client has one of these roles
 */
public boolean clientHasAnyRole(String... roles){
  return OAuth2ExpressionUtils.clientHasAnyRole(authentication,roles);
}
