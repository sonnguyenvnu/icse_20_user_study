protected OAuth2AccessToken retrieveToken(AccessTokenRequest request,OAuth2ProtectedResourceDetails resource,MultiValueMap<String,String> form,HttpHeaders headers) throws OAuth2AccessDeniedException {
  try {
    authenticationHandler.authenticateTokenRequest(resource,form,headers);
    tokenRequestEnhancer.enhance(request,resource,form,headers);
    final AccessTokenRequest copy=request;
    final ResponseExtractor<OAuth2AccessToken> delegate=getResponseExtractor();
    ResponseExtractor<OAuth2AccessToken> extractor=new ResponseExtractor<OAuth2AccessToken>(){
      @Override public OAuth2AccessToken extractData(      ClientHttpResponse response) throws IOException {
        if (response.getHeaders().containsKey("Set-Cookie")) {
          copy.setCookie(response.getHeaders().getFirst("Set-Cookie"));
        }
        return delegate.extractData(response);
      }
    }
;
    return getRestTemplate().execute(getAccessTokenUri(resource,form),getHttpMethod(),getRequestCallback(resource,form,headers),extractor,form.toSingleValueMap());
  }
 catch (  OAuth2Exception oe) {
    throw new OAuth2AccessDeniedException("Access token denied.",resource,oe);
  }
catch (  RestClientException rce) {
    throw new OAuth2AccessDeniedException("Error requesting access token.",resource,rce);
  }
}
