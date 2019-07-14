protected URI getManagementUrl(ServiceInstance instance){
  return UriComponentsBuilder.newInstance().scheme(getManagementScheme(instance)).host(getManagementHost(instance)).port(getManagementPort(instance)).path("/").path(getManagementPath(instance)).build().toUri();
}
