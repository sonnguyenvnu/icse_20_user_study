@RabbitHandler public void receive(MobileMsgTemplate mobileMsgTemplate){
  long startTime=System.currentTimeMillis();
  log.info("?????????????-> ????{} -> ???: {} ",mobileMsgTemplate.getMobile(),mobileMsgTemplate.getContext());
  String channel=mobileMsgTemplate.getChannel();
  SmsMessageHandler messageHandler=messageHandlerMap.get(channel);
  if (messageHandler == null) {
    log.error("??????????????????????");
    return;
  }
  messageHandler.execute(mobileMsgTemplate);
  long useTime=System.currentTimeMillis() - startTime;
  log.info("?? {} ??????????? {}??",channel,useTime);
}
