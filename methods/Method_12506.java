protected Mono<Instance> doUpdateStatus(Instance instance){
  if (!instance.isRegistered()) {
    return Mono.empty();
  }
  log.debug("Update status for {}",instance);
  return instanceWebClient.instance(instance).get().uri(Endpoint.HEALTH).exchange().log(log.getName(),Level.FINEST).flatMap(this::convertStatusInfo).doOnError(ex -> logError(instance,ex)).onErrorResume(this::handleError).map(instance::withStatusInfo);
}
