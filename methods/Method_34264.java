private String getSuccessfulRedirect(AuthorizationRequest authorizationRequest,String authorizationCode){
  if (authorizationCode == null) {
    throw new IllegalStateException("No authorization code found in the current request scope.");
  }
  Map<String,String> query=new LinkedHashMap<String,String>();
  query.put("code",authorizationCode);
  String state=authorizationRequest.getState();
  if (state != null) {
    query.put("state",state);
  }
  return append(authorizationRequest.getRedirectUri(),query,false);
}
