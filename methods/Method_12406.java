@Override public Flux<InstanceEvent> findAll(){
  return Flux.defer(() -> Flux.fromIterable(eventLog.values()).flatMapIterable(Function.identity()).sort(byTimestampAndIdAndVersion));
}
