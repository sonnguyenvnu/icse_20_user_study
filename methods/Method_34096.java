public OAuth2AccessToken refreshAccessToken(OAuth2ProtectedResourceDetails resource,OAuth2RefreshToken refreshToken,AccessTokenRequest request) throws UserRedirectRequiredException, OAuth2AccessDeniedException {
  MultiValueMap<String,String> form=new LinkedMultiValueMap<String,String>();
  form.add("grant_type","refresh_token");
  form.add("refresh_token",refreshToken.getValue());
  try {
    return retrieveToken(request,resource,form,getHeadersForTokenRequest(request));
  }
 catch (  OAuth2AccessDeniedException e) {
    throw getRedirectForAuthorization((AuthorizationCodeResourceDetails)resource,request);
  }
}
