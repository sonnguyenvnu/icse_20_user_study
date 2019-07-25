/** 
 * ??????????POST??
 */
@PostMapping("eventmessage") public Object eventmessage(Map<String,String> param){
  EventMessage msg=new EventMessage();
  BeanUtils.copyProperties(param,msg);
  if (Objects.equals(MessageUtils.MESSAGE_EVENT,msg.getMsgType())) {
    if (Objects.equals(MessageUtils.MESSAGE_SUBSCRIBE,msg.getEvent())) {
      TextMessage text=new TextMessage();
      text=MessageUtils.initText(msg.getToUserName(),msg.getFromUserName(),MessageUtils.menuText());
      return text;
    }
    if (Objects.equals(MessageUtils.MESSAGE_CLICK,msg.getEvent())) {
      TextMessage text=new TextMessage();
      text=MessageUtils.initText(msg.getToUserName(),msg.getFromUserName(),MessageUtils.menuText());
      return text;
    }
    if (Objects.equals(MessageUtils.MESSAGE_VIEW,msg.getEvent())) {
      String url=param.get("EventKey");
      return MessageUtils.initText(msg.getToUserName(),msg.getFromUserName(),url);
    }
    if (Objects.equals(MessageUtils.MESSAGE_SCANCODE,msg.getEvent())) {
      String key=param.get("EventKey");
      return MessageUtils.initText(msg.getToUserName(),msg.getFromUserName(),key);
    }
    if (Objects.equals(MessageUtils.MESSAGE_LOCATION,msg.getEvent())) {
      String label=param.get("Label");
      return MessageUtils.initText(msg.getToUserName(),msg.getFromUserName(),label);
    }
  }
  return "no message";
}
