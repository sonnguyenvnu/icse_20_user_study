@RequestMapping(value="/oauth/authorize") public ModelAndView authorize(Map<String,Object> model,@RequestParam Map<String,String> parameters,SessionStatus sessionStatus,Principal principal){
  AuthorizationRequest authorizationRequest=getOAuth2RequestFactory().createAuthorizationRequest(parameters);
  Set<String> responseTypes=authorizationRequest.getResponseTypes();
  if (!responseTypes.contains("token") && !responseTypes.contains("code")) {
    throw new UnsupportedResponseTypeException("Unsupported response types: " + responseTypes);
  }
  if (authorizationRequest.getClientId() == null) {
    throw new InvalidClientException("A client id must be provided");
  }
  try {
    if (!(principal instanceof Authentication) || !((Authentication)principal).isAuthenticated()) {
      throw new InsufficientAuthenticationException("User must be authenticated with Spring Security before authorization can be completed.");
    }
    ClientDetails client=getClientDetailsService().loadClientByClientId(authorizationRequest.getClientId());
    String redirectUriParameter=authorizationRequest.getRequestParameters().get(OAuth2Utils.REDIRECT_URI);
    String resolvedRedirect=redirectResolver.resolveRedirect(redirectUriParameter,client);
    if (!StringUtils.hasText(resolvedRedirect)) {
      throw new RedirectMismatchException("A redirectUri must be either supplied or preconfigured in the ClientDetails");
    }
    authorizationRequest.setRedirectUri(resolvedRedirect);
    oauth2RequestValidator.validateScope(authorizationRequest,client);
    authorizationRequest=userApprovalHandler.checkForPreApproval(authorizationRequest,(Authentication)principal);
    boolean approved=userApprovalHandler.isApproved(authorizationRequest,(Authentication)principal);
    authorizationRequest.setApproved(approved);
    if (authorizationRequest.isApproved()) {
      if (responseTypes.contains("token")) {
        return getImplicitGrantResponse(authorizationRequest);
      }
      if (responseTypes.contains("code")) {
        return new ModelAndView(getAuthorizationCodeResponse(authorizationRequest,(Authentication)principal));
      }
    }
    model.put(AUTHORIZATION_REQUEST_ATTR_NAME,authorizationRequest);
    model.put(ORIGINAL_AUTHORIZATION_REQUEST_ATTR_NAME,unmodifiableMap(authorizationRequest));
    return getUserApprovalPageResponse(model,authorizationRequest,(Authentication)principal);
  }
 catch (  RuntimeException e) {
    sessionStatus.setComplete();
    throw e;
  }
}
