private boolean isWebSocket(final HttpMessage msg){
  final String connectionHeader=msg.headers().get(CONNECTION);
  final String upgradeHeader=msg.headers().get(UPGRADE);
  return (null != connectionHeader && connectionHeader.equalsIgnoreCase("Upgrade")) || (null != upgradeHeader && upgradeHeader.equalsIgnoreCase("WebSocket"));
}
