protected Mono<DetectedEndpoint> detectEndpoint(Instance instance,EndpointDefinition endpoint){
  URI uri=UriComponentsBuilder.fromUriString(instance.getRegistration().getManagementUrl()).path("/").path(endpoint.getPath()).build().toUri();
  return this.instanceWebClient.instance(instance).options().uri(uri).exchange().flatMap(this.convert(instance.getId(),endpoint,uri)).onErrorResume(e -> {
    log.warn("Endpoint probe for instance {} on endpoint '{}' failed: {}",instance.getId(),uri,e.getMessage());
    log.debug("Endpoint probe for instance {} on endpoint '{}' failed.",instance.getId(),uri,e);
    return Mono.empty();
  }
);
}
