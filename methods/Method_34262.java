private String appendAccessToken(AuthorizationRequest authorizationRequest,OAuth2AccessToken accessToken){
  Map<String,Object> vars=new LinkedHashMap<String,Object>();
  Map<String,String> keys=new HashMap<String,String>();
  if (accessToken == null) {
    throw new InvalidRequestException("An implicit grant could not be made");
  }
  vars.put("access_token",accessToken.getValue());
  vars.put("token_type",accessToken.getTokenType());
  String state=authorizationRequest.getState();
  if (state != null) {
    vars.put("state",state);
  }
  Date expiration=accessToken.getExpiration();
  if (expiration != null) {
    long expires_in=(expiration.getTime() - System.currentTimeMillis()) / 1000;
    vars.put("expires_in",expires_in);
  }
  String originalScope=authorizationRequest.getRequestParameters().get(OAuth2Utils.SCOPE);
  if (originalScope == null || !OAuth2Utils.parseParameterList(originalScope).equals(accessToken.getScope())) {
    vars.put("scope",OAuth2Utils.formatParameterList(accessToken.getScope()));
  }
  Map<String,Object> additionalInformation=accessToken.getAdditionalInformation();
  for (  String key : additionalInformation.keySet()) {
    Object value=additionalInformation.get(key);
    if (value != null) {
      keys.put("extra_" + key,key);
      vars.put("extra_" + key,value);
    }
  }
  return append(authorizationRequest.getRedirectUri(),vars,keys,true);
}
