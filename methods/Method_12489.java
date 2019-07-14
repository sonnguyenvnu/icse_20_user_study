protected Function<ClientResponse,Mono<DetectedEndpoint>> convert(InstanceId instanceId,EndpointDefinition endpointDefinition,URI uri){
  return response -> {
    Mono<DetectedEndpoint> endpoint=Mono.empty();
    if (response.statusCode().is2xxSuccessful()) {
      endpoint=Mono.just(DetectedEndpoint.of(endpointDefinition,uri.toString()));
      log.debug("Endpoint probe for instance {} on endpoint '{}' successful.",instanceId,uri);
    }
 else {
      log.debug("Endpoint probe for instance {} on endpoint '{}' failed with status {}.",instanceId,uri,response.rawStatusCode());
    }
    return response.bodyToMono(Void.class).then(endpoint);
  }
;
}
