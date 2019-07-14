protected Object createMessage(InstanceEvent event,Instance instance){
  Map<String,String> messageJson=new HashMap<>();
  messageJson.put("text",getText(event,instance));
  return messageJson;
}
