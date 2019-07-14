public OAuth2AccessToken obtainAccessToken(OAuth2ProtectedResourceDetails details,AccessTokenRequest request) throws UserRedirectRequiredException, UserApprovalRequiredException, AccessDeniedException, OAuth2AccessDeniedException {
  AuthorizationCodeResourceDetails resource=(AuthorizationCodeResourceDetails)details;
  if (request.getAuthorizationCode() == null) {
    if (request.getStateKey() == null) {
      throw getRedirectForAuthorization(resource,request);
    }
    obtainAuthorizationCode(resource,request);
  }
  return retrieveToken(request,resource,getParametersForTokenRequest(resource,request),getHeadersForTokenRequest(request));
}
