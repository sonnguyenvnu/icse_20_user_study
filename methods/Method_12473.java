protected String getColor(InstanceEvent event){
  if (event instanceof InstanceStatusChangedEvent) {
    return StatusInfo.STATUS_UP.equals(((InstanceStatusChangedEvent)event).getStatusInfo().getStatus()) ? "good" : "danger";
  }
 else {
    return "#439FE0";
  }
}
