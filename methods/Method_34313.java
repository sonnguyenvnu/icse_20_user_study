/** 
 * Check if the current OAuth2 authentication has one of the scopes specified.
 * @param scopes the scopes to check
 * @return true if the OAuth2 token has one of these scopes
 * @throws AccessDeniedException if the scope is invalid and we the flag is set to throw the exception
 */
public boolean hasAnyScope(String... scopes){
  boolean result=OAuth2ExpressionUtils.hasAnyScope(authentication,scopes);
  if (!result) {
    missingScopes.addAll(Arrays.asList(scopes));
  }
  return result;
}
