private MultiValueMap<String,String> getParametersForTokenRequest(AuthorizationCodeResourceDetails resource,AccessTokenRequest request){
  MultiValueMap<String,String> form=new LinkedMultiValueMap<String,String>();
  form.set("grant_type","authorization_code");
  form.set("code",request.getAuthorizationCode());
  Object preservedState=request.getPreservedState();
  if (request.getStateKey() != null || stateMandatory) {
    if (preservedState == null) {
      throw new InvalidRequestException("Possible CSRF detected - state parameter was required but no state could be found");
    }
  }
  String redirectUri=null;
  if (preservedState instanceof String) {
    redirectUri=String.valueOf(preservedState);
  }
 else {
    redirectUri=resource.getRedirectUri(request);
  }
  if (redirectUri != null && !"NONE".equals(redirectUri)) {
    form.set("redirect_uri",redirectUri);
  }
  return form;
}
