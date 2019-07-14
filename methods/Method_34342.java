/** 
 * Create a refreshed authentication.
 * @param authentication The authentication.
 * @param request The scope for the refreshed token.
 * @return The refreshed authentication.
 * @throws InvalidScopeException If the scope requested is invalid or wider than the original scope.
 */
private OAuth2Authentication createRefreshedAuthentication(OAuth2Authentication authentication,TokenRequest request){
  OAuth2Authentication narrowed=authentication;
  Set<String> scope=request.getScope();
  OAuth2Request clientAuth=authentication.getOAuth2Request().refresh(request);
  if (scope != null && !scope.isEmpty()) {
    Set<String> originalScope=clientAuth.getScope();
    if (originalScope == null || !originalScope.containsAll(scope)) {
      throw new InvalidScopeException("Unable to narrow the scope of the client authentication to " + scope + ".",originalScope);
    }
 else {
      clientAuth=clientAuth.narrowScope(scope);
    }
  }
  narrowed=new OAuth2Authentication(clientAuth,authentication.getUserAuthentication());
  return narrowed;
}
