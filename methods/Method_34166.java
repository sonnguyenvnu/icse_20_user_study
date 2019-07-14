private DefaultTokenServices createDefaultTokenServices(){
  DefaultTokenServices tokenServices=new DefaultTokenServices();
  tokenServices.setTokenStore(tokenStore());
  tokenServices.setSupportRefreshToken(true);
  tokenServices.setReuseRefreshToken(reuseRefreshToken);
  tokenServices.setClientDetailsService(clientDetailsService());
  tokenServices.setTokenEnhancer(tokenEnhancer());
  addUserDetailsService(tokenServices,this.userDetailsService);
  return tokenServices;
}
