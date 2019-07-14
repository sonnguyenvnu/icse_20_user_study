@GetMapping(path="/instances/events",produces=MediaType.APPLICATION_JSON_VALUE) public Flux<InstanceEvent> events(){
  return eventStore.findAll();
}
