protected HttpEntity<Map<String,Object>> createHipChatNotification(InstanceEvent event,Instance instance){
  Map<String,Object> body=new HashMap<>();
  body.put("color",getColor(event));
  body.put("message",getMessage(event,instance));
  body.put("notify",getNotify());
  body.put("message_format","html");
  HttpHeaders headers=new HttpHeaders();
  headers.setContentType(MediaType.APPLICATION_JSON);
  return new HttpEntity<>(body,headers);
}
