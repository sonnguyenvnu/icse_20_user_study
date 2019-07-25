/** 
 * Method to send a reply back to Slack after receiving an  {@link Event}. Learn <a href="https://api.slack.com/rtm">more on sending responses to Slack.</a>
 * @param session websocket session between bot and slack
 * @param event   received from slack
 * @param reply   the message to send to slack
 */
protected final void reply(WebSocketSession session,Event event,Message reply){
  try {
    if (StringUtils.isEmpty(reply.getType())) {
      reply.setType(EventType.MESSAGE.name().toLowerCase());
    }
    reply.setText(encode(reply.getText()));
    if (reply.getChannel() == null && event.getChannelId() != null) {
      reply.setChannel(event.getChannelId());
    }
synchronized (sendMessageLock) {
      session.sendMessage(new TextMessage(reply.toJSONString()));
    }
    if (logger.isDebugEnabled()) {
      logger.debug("Reply (Message): {}",reply.toJSONString());
    }
  }
 catch (  IOException e) {
    logger.error("Error sending event: {}. Exception: {}",event.getText(),e.getMessage());
  }
}
