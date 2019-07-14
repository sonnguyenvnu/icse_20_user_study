public boolean supportsResource(OAuth2ProtectedResourceDetails resource){
  return resource instanceof ResourceOwnerPasswordResourceDetails && "password".equals(resource.getGrantType());
}
