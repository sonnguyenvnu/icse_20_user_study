/** 
 * Check if the current OAuth2 authentication has one of the scopes matching a specified regex expression. <pre> access = &quot;#oauth2.hasAnyScopeMatching('admin:manage_scopes','.*_admin:manage_scopes','.*_admin:read_scopes')))&quot; </pre>
 * @param scopesRegex the scopes regex to match
 * @return true if the OAuth2 token has one of these scopes
 * @throws AccessDeniedException if the scope is invalid and we the flag is set to throw the exception
 */
public boolean hasAnyScopeMatching(String... scopesRegex){
  boolean result=OAuth2ExpressionUtils.hasAnyScopeMatching(authentication,scopesRegex);
  if (!result) {
    missingScopes.addAll(Arrays.asList(scopesRegex));
  }
  return result;
}
