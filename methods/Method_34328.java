@Override protected OAuth2Authentication getOAuth2Authentication(ClientDetails client,TokenRequest tokenRequest){
  Map<String,String> parameters=new LinkedHashMap<String,String>(tokenRequest.getRequestParameters());
  String username=parameters.get("username");
  String password=parameters.get("password");
  parameters.remove("password");
  Authentication userAuth=new UsernamePasswordAuthenticationToken(username,password);
  ((AbstractAuthenticationToken)userAuth).setDetails(parameters);
  try {
    userAuth=authenticationManager.authenticate(userAuth);
  }
 catch (  AccountStatusException ase) {
    throw new InvalidGrantException(ase.getMessage());
  }
catch (  BadCredentialsException e) {
    throw new InvalidGrantException(e.getMessage());
  }
  if (userAuth == null || !userAuth.isAuthenticated()) {
    throw new InvalidGrantException("Could not authenticate user: " + username);
  }
  OAuth2Request storedOAuth2Request=getRequestFactory().createOAuth2Request(client,tokenRequest);
  return new OAuth2Authentication(storedOAuth2Request,userAuth);
}
