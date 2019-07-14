public boolean supportsResource(OAuth2ProtectedResourceDetails resource){
  return resource instanceof ClientCredentialsResourceDetails && "client_credentials".equals(resource.getGrantType());
}
