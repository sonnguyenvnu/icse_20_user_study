@Override public Mono<Endpoints> detectEndpoints(Instance instance){
  if (instance.getRegistration().getManagementUrl() == null) {
    log.debug("Endpoint probe for instance {} omitted. No management-url registered.",instance.getId());
    return Mono.empty();
  }
  return Flux.fromIterable(this.endpoints).flatMap(endpoint -> detectEndpoint(instance,endpoint)).collectList().flatMap(this::convert);
}
