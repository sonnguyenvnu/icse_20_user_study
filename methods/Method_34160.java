public AuthorizationServerEndpointsConfigurer tokenGranter(TokenGranter tokenGranter){
  this.tokenGranter=tokenGranter;
  return this;
}
