private String generateCode(AuthorizationRequest authorizationRequest,Authentication authentication) throws AuthenticationException {
  try {
    OAuth2Request storedOAuth2Request=getOAuth2RequestFactory().createOAuth2Request(authorizationRequest);
    OAuth2Authentication combinedAuth=new OAuth2Authentication(storedOAuth2Request,authentication);
    String code=authorizationCodeServices.createAuthorizationCode(combinedAuth);
    return code;
  }
 catch (  OAuth2Exception e) {
    if (authorizationRequest.getState() != null) {
      e.addAdditionalInformation("state",authorizationRequest.getState());
    }
    throw e;
  }
}
