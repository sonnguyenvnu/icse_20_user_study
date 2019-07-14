private ModelAndView handleException(Exception e,ServletWebRequest webRequest) throws Exception {
  ResponseEntity<OAuth2Exception> translate=getExceptionTranslator().translate(e);
  webRequest.getResponse().setStatus(translate.getStatusCode().value());
  if (e instanceof ClientAuthenticationException || e instanceof RedirectMismatchException) {
    return new ModelAndView(errorPage,Collections.singletonMap("error",translate.getBody()));
  }
  AuthorizationRequest authorizationRequest=null;
  try {
    authorizationRequest=getAuthorizationRequestForError(webRequest);
    String requestedRedirectParam=authorizationRequest.getRequestParameters().get(OAuth2Utils.REDIRECT_URI);
    String requestedRedirect=redirectResolver.resolveRedirect(requestedRedirectParam,getClientDetailsService().loadClientByClientId(authorizationRequest.getClientId()));
    authorizationRequest.setRedirectUri(requestedRedirect);
    String redirect=getUnsuccessfulRedirect(authorizationRequest,translate.getBody(),authorizationRequest.getResponseTypes().contains("token"));
    return new ModelAndView(new RedirectView(redirect,false,true,false));
  }
 catch (  OAuth2Exception ex) {
    return new ModelAndView(errorPage,Collections.singletonMap("error",translate.getBody()));
  }
}
