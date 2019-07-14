@Override protected Mono<Void> doNotify(InstanceEvent event,Instance instance){
  if (webhookUrl == null) {
    return Mono.error(new IllegalStateException("'webhookUrl' must not be null."));
  }
  return Mono.fromRunnable(() -> restTemplate.postForEntity(webhookUrl,createDiscordNotification(event,instance),Void.class));
}
