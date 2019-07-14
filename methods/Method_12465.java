protected Map<String,Object> getDetails(InstanceEvent event){
  Map<String,Object> details=new HashMap<>();
  if (event instanceof InstanceStatusChangedEvent) {
    details.put("from",this.getLastStatus(event.getInstance()));
    details.put("to",((InstanceStatusChangedEvent)event).getStatusInfo());
  }
  return details;
}
