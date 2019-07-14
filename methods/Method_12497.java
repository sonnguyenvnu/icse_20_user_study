protected Mono<Info> convertInfo(Instance instance,ClientResponse response){
  if (response.statusCode().is2xxSuccessful() && response.headers().contentType().map(mt -> mt.isCompatibleWith(MediaType.APPLICATION_JSON) || mt.isCompatibleWith(ACTUATOR_V2_MEDIATYPE)).orElse(false)) {
    return response.bodyToMono(RESPONSE_TYPE).map(Info::from).defaultIfEmpty(Info.empty());
  }
  log.info("Couldn't retrieve info for {}: {}",instance,response.statusCode());
  return response.bodyToMono(Void.class).then(Mono.just(Info.empty()));
}
