private boolean login(MqttConnectMessage msg,final String clientId){
  if (msg.variableHeader().hasUserName()) {
    byte[] pwd=null;
    if (msg.variableHeader().hasPassword()) {
      pwd=msg.payload().password().getBytes(StandardCharsets.UTF_8);
    }
 else     if (!brokerConfig.isAllowAnonymous()) {
      LOG.error("Client didn't supply any password and MQTT anonymous mode is disabled CId={}",clientId);
      return false;
    }
    final String login=msg.payload().userName();
    if (!authenticator.checkValid(clientId,login,pwd)) {
      LOG.error("Authenticator has rejected the MQTT credentials CId={}, username={}",clientId,login);
      return false;
    }
    NettyUtils.userName(channel,login);
  }
 else   if (!brokerConfig.isAllowAnonymous()) {
    LOG.error("Client didn't supply any credentials and MQTT anonymous mode is disabled. CId={}",clientId);
    return false;
  }
  return true;
}
