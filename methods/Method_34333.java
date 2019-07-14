private void validateScope(Set<String> requestScopes,Set<String> clientScopes){
  if (clientScopes != null && !clientScopes.isEmpty()) {
    for (    String scope : requestScopes) {
      if (!clientScopes.contains(scope)) {
        throw new InvalidScopeException("Invalid scope: " + scope,clientScopes);
      }
    }
  }
  if (requestScopes.isEmpty()) {
    throw new InvalidScopeException("Empty scope (either the client or the user is not allowed the requested scopes)");
  }
}
