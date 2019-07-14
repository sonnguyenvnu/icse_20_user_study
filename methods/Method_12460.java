protected String getDescription(InstanceEvent event,Instance instance){
  return String.format("Instance %s (%s) went from %s to %s",instance.getRegistration().getName(),instance.getId(),getLastStatus(instance.getId()),((InstanceStatusChangedEvent)event).getStatusInfo().getStatus());
}
