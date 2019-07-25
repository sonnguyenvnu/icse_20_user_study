private Function<ClientResponse,Mono<DetectedEndpoint>> convert(EndpointDefinition endpointDefinition,URI uri){
  return response -> {
    Mono<DetectedEndpoint> endpoint=Mono.empty();
    if (response.statusCode().is2xxSuccessful()) {
      endpoint=Mono.just(DetectedEndpoint.of(endpointDefinition,uri.toString()));
    }
    return response.bodyToMono(Void.class).then(endpoint);
  }
;
}
