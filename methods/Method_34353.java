@Override public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
  MultiValueMap<String,String> formData=new LinkedMultiValueMap<String,String>();
  formData.add(tokenName,accessToken);
  HttpHeaders headers=new HttpHeaders();
  headers.set("Authorization",getAuthorizationHeader(clientId,clientSecret));
  Map<String,Object> map=postForMap(checkTokenEndpointUrl,formData,headers);
  if (map.containsKey("error")) {
    if (logger.isDebugEnabled()) {
      logger.debug("check_token returned error: " + map.get("error"));
    }
    throw new InvalidTokenException(accessToken);
  }
  if (map.containsKey("active") && !"true".equals(String.valueOf(map.get("active")))) {
    logger.debug("check_token returned active attribute: " + map.get("active"));
    throw new InvalidTokenException(accessToken);
  }
  return tokenConverter.extractAuthentication(map);
}
