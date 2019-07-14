protected boolean shouldEndReminder(InstanceEvent event){
  if (event instanceof InstanceDeregisteredEvent) {
    return true;
  }
  if (event instanceof InstanceStatusChangedEvent) {
    return Arrays.binarySearch(this.reminderStatuses,((InstanceStatusChangedEvent)event).getStatusInfo().getStatus()) < 0;
  }
  return false;
}
