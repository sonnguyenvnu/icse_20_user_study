@Override protected Mono<Void> doNotify(InstanceEvent event,Instance instance){
  Message message;
  if (event instanceof InstanceRegisteredEvent) {
    message=getRegisteredMessage(instance);
  }
 else   if (event instanceof InstanceDeregisteredEvent) {
    message=getDeregisteredMessage(instance);
  }
 else   if (event instanceof InstanceStatusChangedEvent) {
    InstanceStatusChangedEvent statusChangedEvent=(InstanceStatusChangedEvent)event;
    message=getStatusChangedMessage(instance,getLastStatus(event.getInstance()),statusChangedEvent.getStatusInfo().getStatus());
  }
 else {
    return Mono.empty();
  }
  HttpHeaders headers=new HttpHeaders();
  headers.setContentType(MediaType.APPLICATION_JSON);
  if (webhookUrl == null) {
    return Mono.error(new IllegalStateException("'webhookUrl' must not be null."));
  }
  return Mono.fromRunnable(() -> this.restTemplate.postForEntity(webhookUrl,new HttpEntity<Object>(message,headers),Void.class));
}
