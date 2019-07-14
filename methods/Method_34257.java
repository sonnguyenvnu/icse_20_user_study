Map<String,Object> unmodifiableMap(AuthorizationRequest authorizationRequest){
  Map<String,Object> authorizationRequestMap=new HashMap<String,Object>();
  authorizationRequestMap.put(OAuth2Utils.CLIENT_ID,authorizationRequest.getClientId());
  authorizationRequestMap.put(OAuth2Utils.STATE,authorizationRequest.getState());
  authorizationRequestMap.put(OAuth2Utils.REDIRECT_URI,authorizationRequest.getRedirectUri());
  if (authorizationRequest.getResponseTypes() != null) {
    authorizationRequestMap.put(OAuth2Utils.RESPONSE_TYPE,Collections.unmodifiableSet(new HashSet<String>(authorizationRequest.getResponseTypes())));
  }
  if (authorizationRequest.getScope() != null) {
    authorizationRequestMap.put(OAuth2Utils.SCOPE,Collections.unmodifiableSet(new HashSet<String>(authorizationRequest.getScope())));
  }
  authorizationRequestMap.put("approved",authorizationRequest.isApproved());
  if (authorizationRequest.getResourceIds() != null) {
    authorizationRequestMap.put("resourceIds",Collections.unmodifiableSet(new HashSet<String>(authorizationRequest.getResourceIds())));
  }
  if (authorizationRequest.getAuthorities() != null) {
    authorizationRequestMap.put("authorities",Collections.unmodifiableSet(new HashSet<GrantedAuthority>(authorizationRequest.getAuthorities())));
  }
  return Collections.unmodifiableMap(authorizationRequestMap);
}
