@SuppressWarnings({"unchecked"}) private Map<String,Object> getMap(String path,String accessToken){
  this.logger.debug("Getting user info from: " + path);
  try {
    OAuth2RestOperations restTemplate=this.restTemplate;
    if (restTemplate == null) {
      BaseOAuth2ProtectedResourceDetails resource=new BaseOAuth2ProtectedResourceDetails();
      resource.setClientId(this.clientId);
      restTemplate=new OAuth2RestTemplate(resource);
    }
    OAuth2AccessToken existingToken=restTemplate.getOAuth2ClientContext().getAccessToken();
    if (existingToken == null || !accessToken.equals(existingToken.getValue())) {
      DefaultOAuth2AccessToken token=new DefaultOAuth2AccessToken(accessToken);
      token.setTokenType(this.tokenType);
      restTemplate.getOAuth2ClientContext().setAccessToken(token);
    }
    return restTemplate.getForEntity(path,Map.class).getBody();
  }
 catch (  Exception ex) {
    this.logger.info("Could not fetch user details: " + ex.getClass() + ", " + ex.getMessage());
    return Collections.<String,Object>singletonMap("error","Could not fetch user details");
  }
}
