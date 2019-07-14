public ResourceServerSecurityConfigurer resourceId(String resourceId){
  this.resourceId=resourceId;
  if (authenticationEntryPoint instanceof OAuth2AuthenticationEntryPoint) {
    ((OAuth2AuthenticationEntryPoint)authenticationEntryPoint).setRealmName(resourceId);
  }
  return this;
}
