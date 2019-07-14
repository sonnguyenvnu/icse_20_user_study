public String obtainAuthorizationCode(OAuth2ProtectedResourceDetails details,AccessTokenRequest request) throws UserRedirectRequiredException, UserApprovalRequiredException, AccessDeniedException, OAuth2AccessDeniedException {
  AuthorizationCodeResourceDetails resource=(AuthorizationCodeResourceDetails)details;
  HttpHeaders headers=getHeadersForAuthorizationRequest(request);
  MultiValueMap<String,String> form=new LinkedMultiValueMap<String,String>();
  if (request.containsKey(OAuth2Utils.USER_OAUTH_APPROVAL)) {
    form.set(OAuth2Utils.USER_OAUTH_APPROVAL,request.getFirst(OAuth2Utils.USER_OAUTH_APPROVAL));
    for (    String scope : details.getScope()) {
      form.set(scopePrefix + scope,request.getFirst(OAuth2Utils.USER_OAUTH_APPROVAL));
    }
  }
 else {
    form.putAll(getParametersForAuthorizeRequest(resource,request));
  }
  authorizationRequestEnhancer.enhance(request,resource,form,headers);
  final AccessTokenRequest copy=request;
  final ResponseExtractor<ResponseEntity<Void>> delegate=getAuthorizationResponseExtractor();
  ResponseExtractor<ResponseEntity<Void>> extractor=new ResponseExtractor<ResponseEntity<Void>>(){
    @Override public ResponseEntity<Void> extractData(    ClientHttpResponse response) throws IOException {
      if (response.getHeaders().containsKey("Set-Cookie")) {
        copy.setCookie(response.getHeaders().getFirst("Set-Cookie"));
      }
      return delegate.extractData(response);
    }
  }
;
  ResponseEntity<Void> response=getRestTemplate().execute(resource.getUserAuthorizationUri(),HttpMethod.POST,getRequestCallback(resource,form,headers),extractor,form.toSingleValueMap());
  if (response.getStatusCode() == HttpStatus.OK) {
    throw getUserApprovalSignal(resource,request);
  }
  URI location=response.getHeaders().getLocation();
  String query=location.getQuery();
  Map<String,String> map=OAuth2Utils.extractMap(query);
  if (map.containsKey("state")) {
    request.setStateKey(map.get("state"));
    if (request.getPreservedState() == null) {
      String redirectUri=resource.getRedirectUri(request);
      if (redirectUri != null) {
        request.setPreservedState(redirectUri);
      }
 else {
        request.setPreservedState(new Object());
      }
    }
  }
  String code=map.get("code");
  if (code == null) {
    throw new UserRedirectRequiredException(location.toString(),form.toSingleValueMap());
  }
  request.set("code",code);
  return code;
}
