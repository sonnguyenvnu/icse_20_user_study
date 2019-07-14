public String resolveRedirect(String requestedRedirect,ClientDetails client) throws OAuth2Exception {
  Set<String> authorizedGrantTypes=client.getAuthorizedGrantTypes();
  if (authorizedGrantTypes.isEmpty()) {
    throw new InvalidGrantException("A client must have at least one authorized grant type.");
  }
  if (!containsRedirectGrantType(authorizedGrantTypes)) {
    throw new InvalidGrantException("A redirect_uri can only be used by implicit or authorization_code grant types.");
  }
  Set<String> registeredRedirectUris=client.getRegisteredRedirectUri();
  if (registeredRedirectUris == null || registeredRedirectUris.isEmpty()) {
    throw new InvalidRequestException("At least one redirect_uri must be registered with the client.");
  }
  return obtainMatchingRedirect(registeredRedirectUris,requestedRedirect);
}
