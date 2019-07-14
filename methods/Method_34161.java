public AuthorizationServerEndpointsConfigurer allowedTokenEndpointRequestMethods(HttpMethod... requestMethods){
  Collections.addAll(allowedTokenEndpointRequestMethods,requestMethods);
  return this;
}
