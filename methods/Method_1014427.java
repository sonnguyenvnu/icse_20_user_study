@Override public void initialize(){
  Configuration configuration=getConfig();
  if (configuration.get("udn") != null) {
    logger.debug("Initializing WemoCoffeeHandler for UDN '{}'",configuration.get("udn"));
    onSubscription();
    onUpdate();
    updateStatus(ThingStatus.ONLINE);
  }
 else {
    logger.debug("Cannot initalize WemoCoffeeHandler. UDN not set.");
  }
}
