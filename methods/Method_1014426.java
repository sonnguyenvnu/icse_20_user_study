@Override public void initialize(){
  logger.debug("Initializing WemoBridgeHandler");
  Configuration configuration=getConfig();
  if (configuration.get(UDN) != null) {
    logger.trace("Initializing WemoBridgeHandler for UDN '{}'",configuration.get(UDN));
    updateStatus(ThingStatus.ONLINE);
  }
 else {
    logger.debug("Cannot initalize WemoBridgeHandler. UDN not set.");
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR);
  }
}
