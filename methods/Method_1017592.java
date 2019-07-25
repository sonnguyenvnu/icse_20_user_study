@PostMapping("/events") public Flux<EventResult> events(@RequestBody Flux<EventData> eventData){
  return eventData.filter(ed -> ed.getEvent() != null).map(ed -> MessageBuilder.withPayload(ed.getEvent()).build()).flatMap(m -> stateMachine.sendEvent(Mono.just(m))).map(EventResult::new);
}
