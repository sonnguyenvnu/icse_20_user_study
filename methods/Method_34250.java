public OAuth2Authentication consumeAuthorizationCode(String code) throws InvalidGrantException {
  OAuth2Authentication auth=this.remove(code);
  if (auth == null) {
    throw new InvalidGrantException("Invalid authorization code: " + code);
  }
  return auth;
}
