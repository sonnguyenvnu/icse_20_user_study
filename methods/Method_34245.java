@Override protected OAuth2Authentication getOAuth2Authentication(ClientDetails client,TokenRequest tokenRequest){
  Map<String,String> parameters=tokenRequest.getRequestParameters();
  String authorizationCode=parameters.get("code");
  String redirectUri=parameters.get(OAuth2Utils.REDIRECT_URI);
  if (authorizationCode == null) {
    throw new InvalidRequestException("An authorization code must be supplied.");
  }
  OAuth2Authentication storedAuth=authorizationCodeServices.consumeAuthorizationCode(authorizationCode);
  if (storedAuth == null) {
    throw new InvalidGrantException("Invalid authorization code: " + authorizationCode);
  }
  OAuth2Request pendingOAuth2Request=storedAuth.getOAuth2Request();
  String redirectUriApprovalParameter=pendingOAuth2Request.getRequestParameters().get(OAuth2Utils.REDIRECT_URI);
  if ((redirectUri != null || redirectUriApprovalParameter != null) && !pendingOAuth2Request.getRedirectUri().equals(redirectUri)) {
    throw new RedirectMismatchException("Redirect URI mismatch.");
  }
  String pendingClientId=pendingOAuth2Request.getClientId();
  String clientId=tokenRequest.getClientId();
  if (clientId != null && !clientId.equals(pendingClientId)) {
    throw new InvalidClientException("Client ID mismatch");
  }
  Map<String,String> combinedParameters=new HashMap<String,String>(pendingOAuth2Request.getRequestParameters());
  combinedParameters.putAll(parameters);
  OAuth2Request finalStoredOAuth2Request=pendingOAuth2Request.createOAuth2Request(combinedParameters);
  Authentication userAuth=storedAuth.getUserAuthentication();
  return new OAuth2Authentication(finalStoredOAuth2Request,userAuth);
}
