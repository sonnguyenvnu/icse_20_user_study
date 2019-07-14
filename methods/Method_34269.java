@RequestMapping(value="/oauth/check_token") @ResponseBody public Map<String,?> checkToken(@RequestParam("token") String value){
  OAuth2AccessToken token=resourceServerTokenServices.readAccessToken(value);
  if (token == null) {
    throw new InvalidTokenException("Token was not recognised");
  }
  if (token.isExpired()) {
    throw new InvalidTokenException("Token has expired");
  }
  OAuth2Authentication authentication=resourceServerTokenServices.loadAuthentication(token.getValue());
  return accessTokenConverter.convertAccessToken(token,authentication);
}
