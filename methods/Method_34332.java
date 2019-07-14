private Set<String> checkUserScopes(Set<String> scopes,ClientDetails clientDetails){
  if (!securityContextAccessor.isUser()) {
    return scopes;
  }
  Set<String> result=new LinkedHashSet<String>();
  Set<String> authorities=AuthorityUtils.authorityListToSet(securityContextAccessor.getAuthorities());
  for (  String scope : scopes) {
    if (authorities.contains(scope) || authorities.contains(scope.toUpperCase()) || authorities.contains("ROLE_" + scope.toUpperCase())) {
      result.add(scope);
    }
  }
  return result;
}
