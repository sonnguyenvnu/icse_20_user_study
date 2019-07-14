protected Mono<Void> updateStatus(InstanceId instanceId){
  return this.statusUpdater.updateStatus(instanceId).onErrorResume(e -> {
    log.warn("Unexpected error while updating status for {}",instanceId,e);
    return Mono.empty();
  }
).doFinally(s -> this.intervalCheck.markAsChecked(instanceId));
}
