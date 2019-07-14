protected Map<String,Object> createPagerdutyEvent(InstanceEvent event,Instance instance){
  Map<String,Object> result=new HashMap<>();
  result.put("service_key",serviceKey);
  result.put("incident_key",instance.getRegistration().getName() + "/" + event.getInstance());
  result.put("description",getDescription(event,instance));
  Map<String,Object> details=getDetails(event);
  result.put("details",details);
  if (event instanceof InstanceStatusChangedEvent) {
    if ("UP".equals(((InstanceStatusChangedEvent)event).getStatusInfo().getStatus())) {
      result.put("event_type","resolve");
    }
 else {
      result.put("event_type","trigger");
      if (client != null) {
        result.put("client",client);
      }
      if (clientUrl != null) {
        result.put("client_url",clientUrl);
      }
      Map<String,Object> context=new HashMap<>();
      context.put("type","link");
      context.put("href",instance.getRegistration().getHealthUrl());
      context.put("text","Application health-endpoint");
      result.put("contexts",singletonList(context));
    }
  }
  return result;
}
