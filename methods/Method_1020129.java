/** 
 * ??????????POST??
 * @throws Exception
 */
@PostMapping("textmessage") public Object textmessage(TextMessage msg) throws Exception {
  logger.info("?????{}",msg.toString());
  if (Objects.equals(MessageUtils.MESSAGE_TEXT,msg.getMsgType())) {
    TextMessage textMessage=new TextMessage();
    if (Objects.equals(KEY_1,msg.getContent())) {
      textMessage=MessageUtils.initText(msg.getToUserName(),msg.getFromUserName(),MessageUtils.firstMenu());
      return textMessage;
    }
    if (Objects.equals(KEY_2,msg.getContent())) {
      NewsMessage newsMessage=MessageUtils.initNewsMessage(msg.getToUserName(),msg.getFromUserName());
      return newsMessage;
    }
    if (Objects.equals(KEY_3,msg.getContent())) {
      textMessage=MessageUtils.initText(msg.getToUserName(),msg.getFromUserName(),MessageUtils.threeMenu());
      return textMessage;
    }
    if (msg.getContent().startsWith(KEY_FY)) {
      String word=msg.getContent().replaceAll("^??","").trim();
      if ("".equals(word)) {
        textMessage=MessageUtils.initText(msg.getToUserName(),msg.getFromUserName(),MessageUtils.threeMenu());
        return textMessage;
      }
      textMessage=MessageUtils.initText(msg.getToUserName(),msg.getFromUserName(),WeixinUtils.translate(word));
      return textMessage;
    }
    if (Objects.equals(KEY_MENU_CN,msg.getContent()) || Objects.equals(KEY_MENU_EN,msg.getContent())) {
      textMessage=MessageUtils.initText(msg.getToUserName(),msg.getFromUserName(),MessageUtils.menuText());
      return textMessage;
    }
    textMessage.setFromUserName(msg.getToUserName());
    textMessage.setToUserName(msg.getFromUserName());
    textMessage.setMsgType(MessageUtils.MESSAGE_TEXT);
    textMessage.setCreateTime(System.currentTimeMillis() + "");
    textMessage.setContent("????????" + msg.getContent());
    return textMessage;
  }
  return null;
}
