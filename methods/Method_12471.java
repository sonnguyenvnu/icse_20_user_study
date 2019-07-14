protected Object createMessage(InstanceEvent event,Instance instance){
  Map<String,Object> messageJson=new HashMap<>();
  messageJson.put("username",username);
  if (icon != null) {
    messageJson.put("icon_emoji",":" + icon + ":");
  }
  if (channel != null) {
    messageJson.put("channel",channel);
  }
  Map<String,Object> attachments=new HashMap<>();
  attachments.put("text",getText(event,instance));
  attachments.put("color",getColor(event));
  attachments.put("mrkdwn_in",Collections.singletonList("text"));
  messageJson.put("attachments",Collections.singletonList(attachments));
  HttpHeaders headers=new HttpHeaders();
  headers.setContentType(MediaType.APPLICATION_JSON);
  return new HttpEntity<>(messageJson,headers);
}
