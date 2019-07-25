@Override public void dispose(){
  logger.debug("Handler disposed.");
  stopLightPolling();
  stopSensorPolling();
  if (hueBridge != null) {
    hueBridge=null;
  }
}
