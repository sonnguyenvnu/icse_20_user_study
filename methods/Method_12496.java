protected Mono<Instance> doUpdateInfo(Instance instance){
  if (instance.getStatusInfo().isOffline() || instance.getStatusInfo().isUnknown()) {
    return Mono.empty();
  }
  if (!instance.getEndpoints().isPresent(Endpoint.INFO)) {
    return Mono.empty();
  }
  log.debug("Update info for {}",instance);
  return instanceWebClient.instance(instance).get().uri(Endpoint.INFO).exchange().log(log.getName(),Level.FINEST).flatMap(response -> convertInfo(instance,response)).onErrorResume(ex -> Mono.just(convertInfo(instance,ex))).map(instance::withInfo);
}
