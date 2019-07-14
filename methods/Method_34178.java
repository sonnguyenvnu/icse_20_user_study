private ResourceServerTokenServices tokenServices(HttpSecurity http){
  if (resourceTokenServices != null) {
    return resourceTokenServices;
  }
  DefaultTokenServices tokenServices=new DefaultTokenServices();
  tokenServices.setTokenStore(tokenStore());
  tokenServices.setSupportRefreshToken(true);
  tokenServices.setClientDetailsService(clientDetails());
  this.resourceTokenServices=tokenServices;
  return tokenServices;
}
