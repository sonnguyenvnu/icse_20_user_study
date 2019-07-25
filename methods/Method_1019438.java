@Override public void handle(Session session) throws Exception {
  sessionManager=getSocksProxyServer().getSessionManager();
  sessionManager.sessionOnCreate(session);
  MethodSelectionMessage msg=new MethodSelectionMessage();
  session.read(msg);
  if (msg.getVersion() != VERSION) {
    throw new ProtocolErrorException();
  }
  SocksMethod selectedMethod=methodSelector.select(msg);
  logger.debug("SESSION[{}] Response client:{}",session.getId(),selectedMethod.getMethodName());
  session.write(new MethodSelectionResponseMessage(VERSION,selectedMethod));
  selectedMethod.doMethod(session);
  CommandMessage commandMessage=new CommandMessage();
  session.read(commandMessage);
  if (commandMessage.hasSocksException()) {
    ServerReply serverReply=commandMessage.getSocksException().getServerReply();
    session.write(new CommandResponseMessage(serverReply));
    logger.info("SESSION[{}] will close, because {}",session.getId(),serverReply);
    return;
  }
  sessionManager.sessionOnCommand(session,commandMessage);
switch (commandMessage.getCommand()) {
case BIND:
    doBind(session,commandMessage);
  break;
case CONNECT:
doConnect(session,commandMessage);
break;
case UDP_ASSOCIATE:
doUDPAssociate(session,commandMessage);
break;
}
}
