protected HttpEntity<?> createRequest(InstanceEvent event,Instance instance){
  Map<String,Object> body=new HashMap<>();
  if (user != null) {
    body.put("user",user);
  }
  if (source != null) {
    body.put("source",source);
  }
  if (event instanceof InstanceStatusChangedEvent && !StatusInfo.STATUS_UP.equals(((InstanceStatusChangedEvent)event).getStatusInfo().getStatus())) {
    body.put("message",getMessage(event,instance));
    body.put("alias",generateAlias(instance));
    body.put("description",getDescription(event,instance));
    if (actions != null) {
      body.put("actions",actions);
    }
    if (tags != null) {
      body.put("tags",tags);
    }
    if (entity != null) {
      body.put("entity",entity);
    }
    Map<String,Object> details=new HashMap<>();
    details.put("type","link");
    details.put("href",instance.getRegistration().getHealthUrl());
    details.put("text","Instance health-endpoint");
    body.put("details",details);
  }
  HttpHeaders headers=new HttpHeaders();
  headers.setContentType(MediaType.APPLICATION_JSON);
  headers.set(HttpHeaders.AUTHORIZATION,"GenieKey " + apiKey);
  return new HttpEntity<>(body,headers);
}
