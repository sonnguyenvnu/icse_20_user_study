protected URI getServiceUrl(ServiceInstance instance){
  return UriComponentsBuilder.fromUri(instance.getUri()).path("/").build().toUri();
}
