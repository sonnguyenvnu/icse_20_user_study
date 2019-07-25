@Override public void initialize(){
  logger.debug("Initializing DeviceHandler.");
  if (getConfig().get(DigitalSTROMBindingConstants.ZONE_ID) != null) {
    final Bridge bridge=getBridge();
    if (bridge != null) {
      bridgeStatusChanged(bridge.getStatusInfo());
    }
 else {
      updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.NONE,"Bridge is missing!");
    }
  }
 else {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"zoneID is missing");
  }
}
