protected void updateLastStatus(InstanceEvent event){
  if (event instanceof InstanceDeregisteredEvent) {
    lastStatuses.remove(event.getInstance());
  }
  if (event instanceof InstanceStatusChangedEvent) {
    lastStatuses.put(event.getInstance(),((InstanceStatusChangedEvent)event).getStatusInfo().getStatus());
  }
}
