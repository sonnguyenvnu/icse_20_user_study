@Override public void dispose(){
  logger.debug("Handler disposed... unregister DeviceStatusListener");
  if (dSID != null) {
    if (dssBridgeHandler != null) {
      dssBridgeHandler.unregisterDeviceStatusListener(this);
    }
  }
  if (device != null) {
    device.setSensorDataRefreshPriority(Config.REFRESH_PRIORITY_NEVER,Config.REFRESH_PRIORITY_NEVER,Config.REFRESH_PRIORITY_NEVER);
  }
  device=null;
}
