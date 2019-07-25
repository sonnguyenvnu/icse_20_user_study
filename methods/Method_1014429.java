@Override public void initialize(){
  wemoLightID=(String)getConfig().get(DEVICE_ID);
  if (getBridge() != null) {
    logger.debug("Initializing WemoLightHandler for LightID '{}'",wemoLightID);
    if (getBridge().getStatus() == ThingStatus.ONLINE) {
      updateStatus(ThingStatus.ONLINE);
      onSubscription();
      onUpdate();
    }
 else {
      updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.OFFLINE.BRIDGE_OFFLINE);
    }
  }
 else {
    updateStatus(ThingStatus.OFFLINE);
  }
}
