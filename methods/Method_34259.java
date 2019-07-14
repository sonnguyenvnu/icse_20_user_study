private boolean isAuthorizationRequestModified(AuthorizationRequest authorizationRequest,Map<String,Object> originalAuthorizationRequest){
  if (!ObjectUtils.nullSafeEquals(authorizationRequest.getClientId(),originalAuthorizationRequest.get(OAuth2Utils.CLIENT_ID))) {
    return true;
  }
  if (!ObjectUtils.nullSafeEquals(authorizationRequest.getState(),originalAuthorizationRequest.get(OAuth2Utils.STATE))) {
    return true;
  }
  if (!ObjectUtils.nullSafeEquals(authorizationRequest.getRedirectUri(),originalAuthorizationRequest.get(OAuth2Utils.REDIRECT_URI))) {
    return true;
  }
  if (!ObjectUtils.nullSafeEquals(authorizationRequest.getResponseTypes(),originalAuthorizationRequest.get(OAuth2Utils.RESPONSE_TYPE))) {
    return true;
  }
  if (!ObjectUtils.nullSafeEquals(authorizationRequest.getScope(),originalAuthorizationRequest.get(OAuth2Utils.SCOPE))) {
    return true;
  }
  if (!ObjectUtils.nullSafeEquals(authorizationRequest.isApproved(),originalAuthorizationRequest.get("approved"))) {
    return true;
  }
  if (!ObjectUtils.nullSafeEquals(authorizationRequest.getResourceIds(),originalAuthorizationRequest.get("resourceIds"))) {
    return true;
  }
  if (!ObjectUtils.nullSafeEquals(authorizationRequest.getAuthorities(),originalAuthorizationRequest.get("authorities"))) {
    return true;
  }
  return false;
}
