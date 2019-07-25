@Override public void dispose(){
  logger.debug("Hue sensor handler disposes. Unregistering listener.");
  if (sensorId != null) {
    HueClient bridgeHandler=getHueClient();
    if (bridgeHandler != null) {
      bridgeHandler.unregisterSensorStatusListener(this);
      hueClient=null;
    }
    sensorId=null;
  }
}
