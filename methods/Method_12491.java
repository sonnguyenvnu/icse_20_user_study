@Override public Mono<Endpoints> detectEndpoints(Instance instance){
  Registration registration=instance.getRegistration();
  String managementUrl=registration.getManagementUrl();
  if (managementUrl == null || Objects.equals(registration.getServiceUrl(),managementUrl)) {
    log.debug("Querying actuator-index for instance {} omitted.",instance.getId());
    return Mono.empty();
  }
  return this.instanceWebClient.instance(instance).get().uri(managementUrl).exchange().flatMap(this.convert(instance,managementUrl)).onErrorResume(e -> {
    log.warn("Querying actuator-index for instance {} on '{}' failed: {}",instance.getId(),managementUrl,e.getMessage());
    log.debug("Querying actuator-index for instance {} on '{}' failed.",instance.getId(),managementUrl,e);
    return Mono.empty();
  }
);
}
