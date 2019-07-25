@Override public void dispose(){
  logger.debug("Handler disposed... unregister DeviceStatusListener");
  if (zoneID != null) {
    if (dssBridgeHandler != null) {
      dssBridgeHandler.unregisterTemperatureControlStatusListener(this);
    }
    temperatureSensorTransmitter=null;
  }
}
