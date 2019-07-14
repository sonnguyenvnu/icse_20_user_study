protected Mono<StatusInfo> convertStatusInfo(ClientResponse response){
  Boolean hasCompatibleContentType=response.headers().contentType().map(mt -> mt.isCompatibleWith(MediaType.APPLICATION_JSON) || mt.isCompatibleWith(ACTUATOR_V2_MEDIATYPE)).orElse(false);
  StatusInfo statusInfoFromStatus=this.getStatusInfoFromStatus(response.statusCode(),emptyMap());
  if (hasCompatibleContentType) {
    return response.bodyToMono(RESPONSE_TYPE).map(body -> {
      if (body.get("status") instanceof String) {
        return StatusInfo.from(body);
      }
      return getStatusInfoFromStatus(response.statusCode(),body);
    }
).defaultIfEmpty(statusInfoFromStatus);
  }
  return response.bodyToMono(Void.class).then(Mono.just(statusInfoFromStatus));
}
