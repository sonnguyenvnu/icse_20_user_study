public ResourceServerSecurityConfigurer tokenServices(ResourceServerTokenServices tokenServices){
  Assert.state(tokenServices != null,"ResourceServerTokenServices cannot be null");
  this.resourceTokenServices=tokenServices;
  return this;
}
