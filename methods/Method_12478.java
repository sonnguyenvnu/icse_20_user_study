private Map<String,Object> createMessage(InstanceEvent event,Instance instance){
  Map<String,Object> parameters=new HashMap<>();
  parameters.put("chat_id",this.chatId);
  parameters.put("parse_mode",this.parseMode);
  parameters.put("disable_notification",this.disableNotify);
  parameters.put("text",getText(event,instance));
  return parameters;
}
