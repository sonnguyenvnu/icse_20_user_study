private void checkClientDetails(OAuth2Authentication auth){
  if (clientDetailsService != null) {
    ClientDetails client;
    try {
      client=clientDetailsService.loadClientByClientId(auth.getOAuth2Request().getClientId());
    }
 catch (    ClientRegistrationException e) {
      throw new OAuth2AccessDeniedException("Invalid token contains invalid client id");
    }
    Set<String> allowed=client.getScope();
    for (    String scope : auth.getOAuth2Request().getScope()) {
      if (!allowed.contains(scope)) {
        throw new OAuth2AccessDeniedException("Invalid token contains disallowed scope (" + scope + ") for this client");
      }
    }
  }
}
