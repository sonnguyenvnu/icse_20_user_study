@Override public void initialize(){
  try {
    BluetoothManager.getBluetoothManager();
  }
 catch (  UnsatisfiedLinkError e) {
    throw new IllegalStateException("BlueZ JNI connection cannot be established.",e);
  }
catch (  RuntimeException e) {
    if (e.getMessage().contains("AccessDenied")) {
      throw new IllegalStateException("Cannot access BlueZ stack due to permission problems. Make sure that your OS user is part of the 'bluetooth' group of BlueZ.");
    }
 else {
      throw new IllegalStateException("Cannot access BlueZ layer.",e);
    }
  }
  Object cfgAddress=getConfig().get(BlueZAdapterConstants.PROPERTY_ADDRESS);
  if (cfgAddress != null) {
    address=new BluetoothAddress(cfgAddress.toString());
  }
 else {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"address not set");
    return;
  }
  Object discovery=getConfig().get(BlueZAdapterConstants.PROPERTY_DISCOVERY);
  if (discovery != null && discovery.toString().equalsIgnoreCase(Boolean.FALSE.toString())) {
    discoveryActive=false;
    logger.debug("Deactivated discovery participation.");
  }
  logger.debug("Creating BlueZ adapter with address '{}'",address);
  for (  tinyb.BluetoothAdapter a : BluetoothManager.getBluetoothManager().getAdapters()) {
    if (a.getAddress().equals(address.toString())) {
      adapter=a;
      updateStatus(ThingStatus.ONLINE);
      if (!adapter.getDiscovering()) {
        adapter.startDiscovery();
      }
      discoveryJob=scheduler.scheduleWithFixedDelay(() -> {
        checkForNewDevices();
      }
,0,10,TimeUnit.SECONDS);
      return;
    }
  }
  updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"No adapter for this address found.");
}
