/** 
 * ??????????POST??
 */
@PostMapping("textmessage") public Object textmessage(TextMessage msg){
  if (Objects.equals(MessageUtils.MESSAGE_TEXT,msg.getMsgType())) {
    TextMessage textMessage=new TextMessage();
    if (Objects.equals(KEY_1,msg.getContent())) {
      textMessage=MessageUtils.initText(msg.getToUserName(),msg.getFromUserName(),MessageUtils.firstMenu());
      return textMessage;
    }
    if (Objects.equals(KEY_2,msg.getContent())) {
      textMessage=MessageUtils.initText(msg.getToUserName(),msg.getFromUserName(),MessageUtils.secondMenu());
      return textMessage;
    }
    if (Objects.equals(KEY_MENU_CN,msg.getContent()) || Objects.equals(KEY_MENU_EN,msg.getContent())) {
      textMessage=MessageUtils.initText(msg.getToUserName(),msg.getFromUserName(),MessageUtils.menuText());
      return textMessage;
    }
    textMessage.setFromUserName(msg.getToUserName());
    textMessage.setToUserName(msg.getFromUserName());
    textMessage.setMsgType(MessageUtils.MESSAGE_TEXT);
    textMessage.setCreateTime(System.currentTimeMillis());
    textMessage.setContent("????????" + msg.getContent());
    return textMessage;
  }
  return null;
}
