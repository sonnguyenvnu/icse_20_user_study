@Override protected Mono<Void> doNotify(InstanceEvent event,Instance instance){
  return Mono.fromRunnable(() -> {
    if (event instanceof InstanceStatusChangedEvent) {
      LOGGER.info("Instance {} ({}) is {}",instance.getRegistration().getName(),event.getInstance(),((InstanceStatusChangedEvent)event).getStatusInfo().getStatus());
    }
 else {
      LOGGER.info("Instance {} ({}) {}",instance.getRegistration().getName(),event.getInstance(),event.getType());
    }
  }
);
}
