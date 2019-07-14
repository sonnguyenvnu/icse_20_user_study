public OAuth2AccessToken obtainAccessToken(OAuth2ProtectedResourceDetails details,AccessTokenRequest request) throws UserRedirectRequiredException, AccessDeniedException, OAuth2AccessDeniedException {
  ClientCredentialsResourceDetails resource=(ClientCredentialsResourceDetails)details;
  return retrieveToken(request,resource,getParametersForTokenRequest(resource),new HttpHeaders());
}
