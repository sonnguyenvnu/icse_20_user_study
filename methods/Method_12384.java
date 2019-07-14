@Override public Flux<Instance> findAll(){
  return this.eventStore.findAll().groupBy(InstanceEvent::getInstance).flatMap(f -> f.reduce(Instance.create(f.key()),Instance::apply));
}
