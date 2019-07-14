@ReadOperation public WebEndpointResponse<Health> getHealth(SecurityContext securityContext){
  return this.responseMapper.map(this.delegate.health(),securityContext,ShowDetails.ALWAYS);
}
