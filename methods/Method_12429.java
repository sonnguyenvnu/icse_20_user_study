@Override protected Mono<Void> doNotify(InstanceEvent event,Instance instance){
  return Mono.fromRunnable(() -> restTemplate.postForEntity(buildUrl(),createHipChatNotification(event,instance),Void.class));
}
