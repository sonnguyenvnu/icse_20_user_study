private Set<String> extractScopes(Map<String,String> requestParameters,String clientId){
  Set<String> scopes=OAuth2Utils.parseParameterList(requestParameters.get(OAuth2Utils.SCOPE));
  ClientDetails clientDetails=clientDetailsService.loadClientByClientId(clientId);
  if ((scopes == null || scopes.isEmpty())) {
    scopes=clientDetails.getScope();
  }
  if (checkUserScopes) {
    scopes=checkUserScopes(scopes,clientDetails);
  }
  return scopes;
}
