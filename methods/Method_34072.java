protected OAuth2AccessToken obtainNewAccessTokenInternal(OAuth2ProtectedResourceDetails details,AccessTokenRequest request) throws UserRedirectRequiredException, AccessDeniedException {
  if (request.isError()) {
    throw OAuth2Exception.valueOf(request.toSingleValueMap());
  }
  for (  AccessTokenProvider tokenProvider : chain) {
    if (tokenProvider.supportsResource(details)) {
      return tokenProvider.obtainAccessToken(details,request);
    }
  }
  throw new OAuth2AccessDeniedException("Unable to obtain a new access token for resource '" + details.getId() + "'. The provider manager is not configured to support it.",details);
}
