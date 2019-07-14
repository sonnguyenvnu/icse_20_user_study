private static long getManifestPublishTimeMsInEmsg(EventMessage eventMessage){
  try {
    return parseXsDateTime(Util.fromUtf8Bytes(eventMessage.messageData));
  }
 catch (  ParserException ignored) {
    return C.TIME_UNSET;
  }
}
