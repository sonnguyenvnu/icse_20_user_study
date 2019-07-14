public OAuth2AccessToken obtainAccessToken(OAuth2ProtectedResourceDetails details,AccessTokenRequest request) throws UserRedirectRequiredException, AccessDeniedException, OAuth2AccessDeniedException {
  ImplicitResourceDetails resource=(ImplicitResourceDetails)details;
  try {
    OAuth2AccessToken token=retrieveToken(request,resource,getParametersForTokenRequest(resource,request),getHeadersForTokenRequest(request));
    if (token == null) {
      throw new UserRedirectRequiredException(resource.getUserAuthorizationUri(),request.toSingleValueMap());
    }
    return token;
  }
 catch (  UserRedirectRequiredException e) {
    throw new UserRedirectRequiredException(e.getRedirectUri(),request.toSingleValueMap());
  }
}
