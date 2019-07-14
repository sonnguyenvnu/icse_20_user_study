protected Mono<Void> updateInfo(InstanceId instanceId){
  return this.infoUpdater.updateInfo(instanceId).onErrorResume(e -> {
    log.warn("Unexpected error while updating info for {}",instanceId,e);
    return Mono.empty();
  }
).doFinally(s -> this.intervalCheck.markAsChecked(instanceId));
}
