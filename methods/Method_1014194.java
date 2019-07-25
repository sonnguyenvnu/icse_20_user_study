@Override public void initialize(){
  try {
    address=new BluetoothAddress(getConfig().get(BluetoothBindingConstants.CONFIGURATION_ADDRESS).toString());
  }
 catch (  IllegalArgumentException e) {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,e.getLocalizedMessage());
    return;
  }
  Bridge bridge=getBridge();
  if (bridge == null) {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"Not associated with any bridge");
    return;
  }
  BridgeHandler bridgeHandler=bridge.getHandler();
  if (!(bridgeHandler instanceof BluetoothAdapter)) {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"Associated with an unsupported bridge");
    return;
  }
  adapter=(BluetoothAdapter)bridgeHandler;
  try {
    deviceLock.lock();
    device=adapter.getDevice(address);
    device.addListener(this);
  }
  finally {
    deviceLock.unlock();
  }
  updateStatus(ThingStatus.UNKNOWN);
}
