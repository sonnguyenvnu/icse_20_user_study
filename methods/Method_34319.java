@Override protected OAuth2Authentication getOAuth2Authentication(ClientDetails client,TokenRequest clientToken){
  Authentication userAuth=SecurityContextHolder.getContext().getAuthentication();
  if (userAuth == null || !userAuth.isAuthenticated()) {
    throw new InsufficientAuthenticationException("There is no currently logged in user");
  }
  Assert.state(clientToken instanceof ImplicitTokenRequest,"An ImplicitTokenRequest is required here. Caller needs to wrap the TokenRequest.");
  OAuth2Request requestForStorage=((ImplicitTokenRequest)clientToken).getOAuth2Request();
  return new OAuth2Authentication(requestForStorage,userAuth);
}
