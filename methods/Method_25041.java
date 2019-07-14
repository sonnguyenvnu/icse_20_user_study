protected boolean isWebsocketRequested(IHTTPSession session){
  Map<String,String> headers=session.getHeaders();
  String upgrade=headers.get(NanoWSD.HEADER_UPGRADE);
  boolean isCorrectConnection=isWebSocketConnectionHeader(headers);
  boolean isUpgrade=NanoWSD.HEADER_UPGRADE_VALUE.equalsIgnoreCase(upgrade);
  return isUpgrade && isCorrectConnection;
}
