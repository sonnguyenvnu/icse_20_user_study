public boolean supportsResource(OAuth2ProtectedResourceDetails resource){
  return resource instanceof AuthorizationCodeResourceDetails && "authorization_code".equals(resource.getGrantType());
}
