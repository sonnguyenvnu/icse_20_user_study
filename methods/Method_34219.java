@Override public AuthorizationRequest checkForPreApproval(AuthorizationRequest authorizationRequest,Authentication userAuthentication){
  boolean approved=false;
  String clientId=authorizationRequest.getClientId();
  Set<String> scopes=authorizationRequest.getScope();
  if (clientDetailsService != null) {
    try {
      ClientDetails client=clientDetailsService.loadClientByClientId(clientId);
      approved=true;
      for (      String scope : scopes) {
        if (!client.isAutoApprove(scope)) {
          approved=false;
        }
      }
      if (approved) {
        authorizationRequest.setApproved(true);
        return authorizationRequest;
      }
    }
 catch (    ClientRegistrationException e) {
      logger.warn("Client registration problem prevent autoapproval check for client=" + clientId);
    }
  }
  OAuth2Request storedOAuth2Request=requestFactory.createOAuth2Request(authorizationRequest);
  OAuth2Authentication authentication=new OAuth2Authentication(storedOAuth2Request,userAuthentication);
  if (logger.isDebugEnabled()) {
    StringBuilder builder=new StringBuilder("Looking up existing token for ");
    builder.append("client_id=" + clientId);
    builder.append(", scope=" + scopes);
    builder.append(" and username=" + userAuthentication.getName());
    logger.debug(builder.toString());
  }
  OAuth2AccessToken accessToken=tokenStore.getAccessToken(authentication);
  if (logger.isDebugEnabled()) {
    logger.debug("Existing access token=" + accessToken);
  }
  if (accessToken != null && !accessToken.isExpired()) {
    if (logger.isDebugEnabled()) {
      logger.debug("User already approved with token=" + accessToken);
    }
    approved=true;
  }
 else {
    logger.debug("Checking explicit approval");
    approved=userAuthentication.isAuthenticated() && approved;
  }
  authorizationRequest.setApproved(approved);
  return authorizationRequest;
}
