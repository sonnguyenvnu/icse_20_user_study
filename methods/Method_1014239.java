@Override public boolean connect(){
  if (device != null && !device.getConnected()) {
    try {
      return device.connect();
    }
 catch (    BluetoothException e) {
      if ("Timeout was reached".equals(e.getMessage())) {
        notifyListeners(BluetoothEventType.CONNECTION_STATE,new BluetoothConnectionStatusNotification(ConnectionState.DISCONNECTED));
      }
 else       if (e.getMessage() != null && e.getMessage().contains("Protocol not available")) {
        logger.warn("Bluetooth device '{}' does not allow a connection.",device.getAddress());
      }
 else {
        logger.debug("Exception occurred when trying to connect device '{}': {}",device.getAddress(),e.getMessage());
      }
    }
  }
  return false;
}
