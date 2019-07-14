/** 
 * Check if any scope decisions have been denied in the current context and throw an exception if so. This method automatically wraps any expressions when using  {@link OAuth2MethodSecurityExpressionHandler} or{@link OAuth2WebSecurityExpressionHandler}. OAuth2Example usage: <pre> access = &quot;#oauth2.hasScope('read') or (#oauth2.hasScope('other') and hasRole('ROLE_USER'))&quot; </pre> Will automatically be wrapped to ensure that explicit errors are propagated rather than a generic error when returning false: <pre> access = &quot;#oauth2.throwOnError(#oauth2.hasScope('read') or (#oauth2.hasScope('other') and hasRole('ROLE_USER'))&quot; </pre> N.B. normally this method will be automatically wrapped around all your access expressions. You could use it explicitly to get more control, or if you have registered your own <code>ExpressionParser</code> you might need it.
 * @param decision the existing access decision
 * @return true if the OAuth2 token has one of these scopes
 * @throws InsufficientScopeException if the scope is invalid and we the flag is set to throw the exception
 */
public boolean throwOnError(boolean decision){
  if (!decision && !missingScopes.isEmpty()) {
    Throwable failure=new InsufficientScopeException("Insufficient scope for this resource",missingScopes);
    throw new AccessDeniedException(failure.getMessage(),failure);
  }
  return decision;
}
