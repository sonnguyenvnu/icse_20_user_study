private ModelAndView getImplicitGrantResponse(AuthorizationRequest authorizationRequest){
  try {
    TokenRequest tokenRequest=getOAuth2RequestFactory().createTokenRequest(authorizationRequest,"implicit");
    OAuth2Request storedOAuth2Request=getOAuth2RequestFactory().createOAuth2Request(authorizationRequest);
    OAuth2AccessToken accessToken=getAccessTokenForImplicitGrant(tokenRequest,storedOAuth2Request);
    if (accessToken == null) {
      throw new UnsupportedResponseTypeException("Unsupported response type: token");
    }
    return new ModelAndView(new RedirectView(appendAccessToken(authorizationRequest,accessToken),false,true,false));
  }
 catch (  OAuth2Exception e) {
    return new ModelAndView(new RedirectView(getUnsuccessfulRedirect(authorizationRequest,e,true),false,true,false));
  }
}
