protected URI getHealthUrl(ServiceInstance instance){
  return UriComponentsBuilder.fromUri(getManagementUrl(instance)).path("/").path(getHealthPath(instance)).build().toUri();
}
