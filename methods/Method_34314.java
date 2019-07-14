/** 
 * Check if the current OAuth2 authentication has one of the scopes matching a specified regex expression. <pre> access = &quot;#oauth2.hasScopeMatching('.*_admin:manage_scopes')))&quot; </pre>
 * @param scopeRegex the scope regex to match
 * @return true if the OAuth2 authentication has the required scope
 */
public boolean hasScopeMatching(String scopeRegex){
  return hasAnyScopeMatching(scopeRegex);
}
