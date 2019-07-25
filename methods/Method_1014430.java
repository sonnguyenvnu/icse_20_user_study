@Override public void initialize(){
  Configuration configuration=getConfig();
  if (configuration.get("udn") != null) {
    logger.debug("Initializing WemoMakerHandler for UDN '{}'",configuration.get("udn"));
    onUpdate();
    updateStatus(ThingStatus.ONLINE);
  }
 else {
    logger.debug("Cannot initalize WemoMakerHandler. UDN not set.");
  }
}
