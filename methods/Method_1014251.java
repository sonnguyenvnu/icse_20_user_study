@Override public void dispose(){
  logger.debug("Handler disposed... unregister DeviceStatusListener");
  if (dSID != null) {
    if (dssBridgeHandler != null) {
      dssBridgeHandler.unregisterDeviceStatusListener(this);
    }
  }
  circuit=null;
}
