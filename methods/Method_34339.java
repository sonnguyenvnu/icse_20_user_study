public String extractKey(OAuth2Authentication authentication){
  Map<String,String> values=new LinkedHashMap<String,String>();
  OAuth2Request authorizationRequest=authentication.getOAuth2Request();
  if (!authentication.isClientOnly()) {
    values.put(USERNAME,authentication.getName());
  }
  values.put(CLIENT_ID,authorizationRequest.getClientId());
  if (authorizationRequest.getScope() != null) {
    values.put(SCOPE,OAuth2Utils.formatParameterList(new TreeSet<String>(authorizationRequest.getScope())));
  }
  return generateKey(values);
}
