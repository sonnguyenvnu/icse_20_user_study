public OAuth2Request createOAuth2Request(){
  return new OAuth2Request(getRequestParameters(),getClientId(),getAuthorities(),isApproved(),getScope(),getResourceIds(),getRedirectUri(),getResponseTypes(),getExtensions());
}
