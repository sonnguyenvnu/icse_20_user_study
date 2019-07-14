@GetMapping(path="/instances/events",produces=MediaType.TEXT_EVENT_STREAM_VALUE) public Flux<ServerSentEvent<InstanceEvent>> eventStream(){
  return Flux.from(eventStore).map(event -> ServerSentEvent.builder(event).build()).mergeWith(ping());
}
