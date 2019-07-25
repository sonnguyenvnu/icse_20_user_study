@Deactivate protected void deactivate(){
  if (commonHttpClient != null) {
    try {
      commonHttpClient.stop();
    }
 catch (    Exception e) {
      logger.error("error while stopping shared Jetty http client",e);
    }
    commonHttpClient=null;
    logger.debug("Jetty shared http client stopped");
  }
  if (commonWebSocketClient != null) {
    try {
      commonWebSocketClient.stop();
    }
 catch (    Exception e) {
      logger.error("error while stopping shared Jetty web socket client",e);
    }
    commonWebSocketClient=null;
    logger.debug("Jetty shared web socket client stopped");
  }
  threadPool=null;
}
