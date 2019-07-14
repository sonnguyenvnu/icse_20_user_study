public boolean supportsResource(OAuth2ProtectedResourceDetails resource){
  return resource instanceof ImplicitResourceDetails && "implicit".equals(resource.getGrantType());
}
