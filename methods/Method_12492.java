protected Function<ClientResponse,Mono<Endpoints>> convert(Instance instance,String managementUrl){
  return response -> {
    if (!response.statusCode().is2xxSuccessful()) {
      log.debug("Querying actuator-index for instance {} on '{}' failed with status {}.",instance.getId(),managementUrl,response.rawStatusCode());
      return response.bodyToMono(Void.class).then(Mono.empty());
    }
    if (!response.headers().contentType().map(actuatorMediaType::isCompatibleWith).orElse(false)) {
      log.debug("Querying actuator-index for instance {} on '{}' failed with incompatible Content-Type '{}'.",instance.getId(),managementUrl,response.headers().contentType().map(Objects::toString).orElse("(missing)"));
      return response.bodyToMono(Void.class).then(Mono.empty());
    }
    log.debug("Querying actuator-index for instance {} on '{}' successful.",instance.getId(),managementUrl);
    return response.bodyToMono(Response.class).flatMap(this::convertResponse).map(this.alignWithManagementUrl(instance.getId(),managementUrl));
  }
;
}
