@GetMapping(path="/applications",produces=MediaType.TEXT_EVENT_STREAM_VALUE) public Flux<ServerSentEvent<Application>> applicationsStream(){
  return Flux.from(eventPublisher).flatMap(event -> registry.getInstance(event.getInstance())).map(this::getApplicationForInstance).flatMap(group -> toApplication(group.getT1(),group.getT2())).map(application -> ServerSentEvent.builder(application).build()).mergeWith(ping());
}
