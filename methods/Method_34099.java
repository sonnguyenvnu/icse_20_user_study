private MultiValueMap<String,String> getParametersForAuthorizeRequest(AuthorizationCodeResourceDetails resource,AccessTokenRequest request){
  MultiValueMap<String,String> form=new LinkedMultiValueMap<String,String>();
  form.set("response_type","code");
  form.set("client_id",resource.getClientId());
  if (request.get("scope") != null) {
    form.set("scope",request.getFirst("scope"));
  }
 else {
    form.set("scope",OAuth2Utils.formatParameterList(resource.getScope()));
  }
  String redirectUri=resource.getPreEstablishedRedirectUri();
  Object preservedState=request.getPreservedState();
  if (redirectUri == null && preservedState != null) {
    redirectUri=String.valueOf(preservedState);
  }
 else {
    redirectUri=request.getCurrentUri();
  }
  String stateKey=request.getStateKey();
  if (stateKey != null) {
    form.set("state",stateKey);
    if (preservedState == null) {
      throw new InvalidRequestException("Possible CSRF detected - state parameter was present but no state could be found");
    }
  }
  if (redirectUri != null) {
    form.set("redirect_uri",redirectUri);
  }
  return form;
}
