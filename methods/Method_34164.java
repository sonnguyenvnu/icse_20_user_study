private Set<HttpMethod> allowedTokenEndpointRequestMethods(){
  if (allowedTokenEndpointRequestMethods.isEmpty()) {
    allowedTokenEndpointRequestMethods.add(HttpMethod.POST);
  }
  return allowedTokenEndpointRequestMethods;
}
