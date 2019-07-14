private Collection<OAuth2AccessToken> enhance(Collection<OAuth2AccessToken> tokens){
  Collection<OAuth2AccessToken> result=new ArrayList<OAuth2AccessToken>();
  for (  OAuth2AccessToken prototype : tokens) {
    DefaultOAuth2AccessToken token=new DefaultOAuth2AccessToken(prototype);
    OAuth2Authentication authentication=tokenStore.readAuthentication(token);
    if (authentication == null) {
      continue;
    }
    String clientId=authentication.getOAuth2Request().getClientId();
    if (clientId != null) {
      Map<String,Object> map=new HashMap<String,Object>(token.getAdditionalInformation());
      map.put("client_id",clientId);
      token.setAdditionalInformation(map);
      result.add(token);
    }
  }
  return result;
}
