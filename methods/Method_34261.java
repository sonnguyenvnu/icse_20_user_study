private View getAuthorizationCodeResponse(AuthorizationRequest authorizationRequest,Authentication authUser){
  try {
    return new RedirectView(getSuccessfulRedirect(authorizationRequest,generateCode(authorizationRequest,authUser)),false,true,false);
  }
 catch (  OAuth2Exception e) {
    return new RedirectView(getUnsuccessfulRedirect(authorizationRequest,e,false),false,true,false);
  }
}
