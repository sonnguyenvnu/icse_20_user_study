/** 
 * Check if the OAuth2 client (not the user) has the role specified. To check the user's roles see {@link #clientHasRole(String)}.
 * @param role the role to check
 * @return true if the OAuth2 client has this role
 */
public boolean clientHasRole(String role){
  return clientHasAnyRole(role);
}
