private UserRedirectRequiredException getRedirectForAuthorization(AuthorizationCodeResourceDetails resource,AccessTokenRequest request){
  TreeMap<String,String> requestParameters=new TreeMap<String,String>();
  requestParameters.put("response_type","code");
  requestParameters.put("client_id",resource.getClientId());
  String redirectUri=resource.getRedirectUri(request);
  if (redirectUri != null) {
    requestParameters.put("redirect_uri",redirectUri);
  }
  if (resource.isScoped()) {
    StringBuilder builder=new StringBuilder();
    List<String> scope=resource.getScope();
    if (scope != null) {
      Iterator<String> scopeIt=scope.iterator();
      while (scopeIt.hasNext()) {
        builder.append(scopeIt.next());
        if (scopeIt.hasNext()) {
          builder.append(' ');
        }
      }
    }
    requestParameters.put("scope",builder.toString());
  }
  UserRedirectRequiredException redirectException=new UserRedirectRequiredException(resource.getUserAuthorizationUri(),requestParameters);
  String stateKey=stateKeyGenerator.generateKey(resource);
  redirectException.setStateKey(stateKey);
  request.setStateKey(stateKey);
  redirectException.setStateToPreserve(redirectUri);
  request.setPreservedState(redirectUri);
  return redirectException;
}
