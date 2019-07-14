@RequestMapping(value="/oauth/authorize",method=RequestMethod.POST,params=OAuth2Utils.USER_OAUTH_APPROVAL) public View approveOrDeny(@RequestParam Map<String,String> approvalParameters,Map<String,?> model,SessionStatus sessionStatus,Principal principal){
  if (!(principal instanceof Authentication)) {
    sessionStatus.setComplete();
    throw new InsufficientAuthenticationException("User must be authenticated with Spring Security before authorizing an access token.");
  }
  AuthorizationRequest authorizationRequest=(AuthorizationRequest)model.get(AUTHORIZATION_REQUEST_ATTR_NAME);
  if (authorizationRequest == null) {
    sessionStatus.setComplete();
    throw new InvalidRequestException("Cannot approve uninitialized authorization request.");
  }
  @SuppressWarnings("unchecked") Map<String,Object> originalAuthorizationRequest=(Map<String,Object>)model.get(ORIGINAL_AUTHORIZATION_REQUEST_ATTR_NAME);
  if (isAuthorizationRequestModified(authorizationRequest,originalAuthorizationRequest)) {
    throw new InvalidRequestException("Changes were detected from the original authorization request.");
  }
  try {
    Set<String> responseTypes=authorizationRequest.getResponseTypes();
    authorizationRequest.setApprovalParameters(approvalParameters);
    authorizationRequest=userApprovalHandler.updateAfterApproval(authorizationRequest,(Authentication)principal);
    boolean approved=userApprovalHandler.isApproved(authorizationRequest,(Authentication)principal);
    authorizationRequest.setApproved(approved);
    if (authorizationRequest.getRedirectUri() == null) {
      sessionStatus.setComplete();
      throw new InvalidRequestException("Cannot approve request when no redirect URI is provided.");
    }
    if (!authorizationRequest.isApproved()) {
      return new RedirectView(getUnsuccessfulRedirect(authorizationRequest,new UserDeniedAuthorizationException("User denied access"),responseTypes.contains("token")),false,true,false);
    }
    if (responseTypes.contains("token")) {
      return getImplicitGrantResponse(authorizationRequest).getView();
    }
    return getAuthorizationCodeResponse(authorizationRequest,(Authentication)principal);
  }
  finally {
    sessionStatus.setComplete();
  }
}
