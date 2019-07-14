private String getUnsuccessfulRedirect(AuthorizationRequest authorizationRequest,OAuth2Exception failure,boolean fragment){
  if (authorizationRequest == null || authorizationRequest.getRedirectUri() == null) {
    throw new UnapprovedClientAuthenticationException("Authorization failure, and no redirect URI.",failure);
  }
  Map<String,String> query=new LinkedHashMap<String,String>();
  query.put("error",failure.getOAuth2ErrorCode());
  query.put("error_description",failure.getMessage());
  if (authorizationRequest.getState() != null) {
    query.put("state",authorizationRequest.getState());
  }
  if (failure.getAdditionalInformation() != null) {
    for (    Map.Entry<String,String> additionalInfo : failure.getAdditionalInformation().entrySet()) {
      query.put(additionalInfo.getKey(),additionalInfo.getValue());
    }
  }
  return append(authorizationRequest.getRedirectUri(),query,fragment);
}
