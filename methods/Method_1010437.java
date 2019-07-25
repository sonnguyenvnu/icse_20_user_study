public void info(@NotNull String msg){
  if (isLevelEnabled(Level.INFO)) {
    myHandler.handle(Message.createMessage(MessageKind.INFORMATION,mySender.toString(),msg));
    Log4jUtil.info(myTraceHandler,msg,null,null);
  }
}
