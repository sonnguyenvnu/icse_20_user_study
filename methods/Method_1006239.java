public boolean ping(){
  try (Protocol protocol=openNewConnection()){
    protocol.sendMessage(RemoteMessage.PING);
    Pair<RemoteMessage,Object> response=protocol.receiveMessage();
    if (response.getKey() == RemoteMessage.PONG && Protocol.IDENTIFIER.equals(response.getValue())) {
      return true;
    }
 else {
      String port=String.valueOf(this.port);
      String errorMessage=Localization.lang("Cannot use port %0 for remote operation; another application may be using it. Try specifying another port.",port);
      LOGGER.error(errorMessage);
      return false;
    }
  }
 catch (  IOException e) {
    LOGGER.debug("Could not ping server at port " + port,e);
    return false;
  }
}
