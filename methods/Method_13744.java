@Override public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
  Map<String,Object> map=getMap(this.userInfoEndpointUrl,accessToken);
  if (map.containsKey("error")) {
    this.logger.debug("userinfo returned error: " + map.get("error"));
    throw new InvalidTokenException(accessToken);
  }
  return extractAuthentication(map);
}
