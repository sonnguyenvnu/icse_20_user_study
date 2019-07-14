/** 
 * @param principal the currently authentication principal
 * @return a client id if there is one in the principal
 */
protected String getClientId(Principal principal){
  Authentication client=(Authentication)principal;
  if (!client.isAuthenticated()) {
    throw new InsufficientAuthenticationException("The client is not authenticated.");
  }
  String clientId=client.getName();
  if (client instanceof OAuth2Authentication) {
    clientId=((OAuth2Authentication)client).getOAuth2Request().getClientId();
  }
  return clientId;
}
