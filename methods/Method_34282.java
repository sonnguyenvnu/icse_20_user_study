@RequestMapping(value="/oauth/token",method=RequestMethod.POST) public ResponseEntity<OAuth2AccessToken> postAccessToken(Principal principal,@RequestParam Map<String,String> parameters) throws HttpRequestMethodNotSupportedException {
  if (!(principal instanceof Authentication)) {
    throw new InsufficientAuthenticationException("There is no client authentication. Try adding an appropriate authentication filter.");
  }
  String clientId=getClientId(principal);
  ClientDetails authenticatedClient=getClientDetailsService().loadClientByClientId(clientId);
  TokenRequest tokenRequest=getOAuth2RequestFactory().createTokenRequest(parameters,authenticatedClient);
  if (clientId != null && !clientId.equals("")) {
    if (!clientId.equals(tokenRequest.getClientId())) {
      throw new InvalidClientException("Given client ID does not match authenticated client");
    }
  }
  if (authenticatedClient != null) {
    oAuth2RequestValidator.validateScope(tokenRequest,authenticatedClient);
  }
  if (!StringUtils.hasText(tokenRequest.getGrantType())) {
    throw new InvalidRequestException("Missing grant type");
  }
  if (tokenRequest.getGrantType().equals("implicit")) {
    throw new InvalidGrantException("Implicit grant type not supported from token endpoint");
  }
  if (isAuthCodeRequest(parameters)) {
    if (!tokenRequest.getScope().isEmpty()) {
      logger.debug("Clearing scope of incoming token request");
      tokenRequest.setScope(Collections.<String>emptySet());
    }
  }
  if (isRefreshTokenRequest(parameters)) {
    tokenRequest.setScope(OAuth2Utils.parseParameterList(parameters.get(OAuth2Utils.SCOPE)));
  }
  OAuth2AccessToken token=getTokenGranter().grant(tokenRequest.getGrantType(),tokenRequest);
  if (token == null) {
    throw new UnsupportedGrantTypeException("Unsupported grant type: " + tokenRequest.getGrantType());
  }
  return getResponse(token);
}
