/** 
 * ??????????POST??
 */
@PostMapping("eventmessage") public Object eventmessage(EventMessage msg){
  if (Objects.equals(MessageUtils.MESSAGE_EVENT,msg.getMsgType())) {
    if (Objects.equals(MessageUtils.MESSAGE_SUBSCRIBE,msg.getEvent())) {
      TextMessage text=MessageUtils.initText(msg.getToUserName(),msg.getFromUserName(),MessageUtils.menuText());
      return text;
    }
  }
  return null;
}
