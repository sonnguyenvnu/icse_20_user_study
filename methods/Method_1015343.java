@Override public Flux<InstanceEvent> find(InstanceId id){
  return Flux.defer(() -> Flux.fromIterable(eventLog.getOrDefault(id,Collections.emptyList())));
}
