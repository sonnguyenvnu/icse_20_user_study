@Override protected Mono<Void> doNotify(InstanceEvent event,Instance instance){
  return Mono.fromRunnable(() -> restTemplate.getForObject(buildUrl(),Void.class,createMessage(event,instance)));
}
