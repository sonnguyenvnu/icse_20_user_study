@Override public Flux<Instance> findAll(){
  return Mono.fromSupplier(this.snapshots::values).flatMapIterable(Function.identity());
}
