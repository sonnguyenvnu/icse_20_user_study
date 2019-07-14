@GetMapping(path="/instances/{id}",produces=MediaType.TEXT_EVENT_STREAM_VALUE) public Flux<ServerSentEvent<Instance>> instanceStream(@PathVariable String id){
  return Flux.from(eventStore).filter(event -> event.getInstance().equals(InstanceId.of(id))).flatMap(event -> registry.getInstance(event.getInstance())).map(event -> ServerSentEvent.builder(event).build()).mergeWith(ping());
}
