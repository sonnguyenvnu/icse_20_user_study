public OAuth2AccessToken obtainAccessToken(OAuth2ProtectedResourceDetails resource,AccessTokenRequest request) throws UserRedirectRequiredException, AccessDeniedException {
  OAuth2AccessToken accessToken=null;
  OAuth2AccessToken existingToken=null;
  Authentication auth=SecurityContextHolder.getContext().getAuthentication();
  if (auth instanceof AnonymousAuthenticationToken) {
    if (!resource.isClientOnly()) {
      throw new InsufficientAuthenticationException("Authentication is required to obtain an access token (anonymous not allowed)");
    }
  }
  if (resource.isClientOnly() || (auth != null && auth.isAuthenticated())) {
    existingToken=request.getExistingToken();
    if (existingToken == null && clientTokenServices != null) {
      existingToken=clientTokenServices.getAccessToken(resource,auth);
    }
    if (existingToken != null) {
      if (existingToken.isExpired()) {
        if (clientTokenServices != null) {
          clientTokenServices.removeAccessToken(resource,auth);
        }
        OAuth2RefreshToken refreshToken=existingToken.getRefreshToken();
        if (refreshToken != null && !resource.isClientOnly()) {
          accessToken=refreshAccessToken(resource,refreshToken,request);
        }
      }
 else {
        accessToken=existingToken;
      }
    }
  }
  if (accessToken == null) {
    accessToken=obtainNewAccessTokenInternal(resource,request);
    if (accessToken == null) {
      throw new IllegalStateException("An OAuth 2 access token must be obtained or an exception thrown.");
    }
  }
  if (clientTokenServices != null && (resource.isClientOnly() || auth != null && auth.isAuthenticated())) {
    clientTokenServices.saveAccessToken(resource,auth,accessToken);
  }
  return accessToken;
}
