@Override public void initialize(){
  logger.debug("Initializing CircuitHandler.");
  if (StringUtils.isNotBlank((String)getConfig().get(DigitalSTROMBindingConstants.DEVICE_DSID))) {
    dSID=getConfig().get(DigitalSTROMBindingConstants.DEVICE_DSID).toString();
    final Bridge bridge=getBridge();
    if (bridge != null) {
      bridgeStatusChanged(bridge.getStatusInfo());
    }
 else {
      updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.NONE,"Bridge is missing!");
    }
  }
 else {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"dSID is missing");
  }
}
