@Override public void initialize(){
  logger.debug("Initializing thing {}",getThing().getUID());
  config=getConfigAs(LIRCRemoteConfiguration.class);
  remoteName=config.getRemote();
  if (remoteName == null) {
    logger.error("Remote name is not set in {}",getThing().getUID());
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"Remote name is not set");
  }
 else {
    bridgeHandler=(LIRCBridgeHandler)getBridge().getHandler();
    bridgeHandler.registerMessageListener(this);
    if (getBridge().getStatus() == ThingStatus.ONLINE) {
      updateStatus(ThingStatus.ONLINE);
    }
 else {
      updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.BRIDGE_OFFLINE);
    }
  }
}
