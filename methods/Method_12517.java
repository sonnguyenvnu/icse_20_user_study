@GetMapping(path="/applications/{name}",produces=MediaType.APPLICATION_JSON_VALUE) public Mono<ResponseEntity<Application>> application(@PathVariable("name") String name){
  return this.toApplication(name,registry.getInstances(name).filter(Instance::isRegistered)).filter(a -> !a.getInstances().isEmpty()).map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
}
