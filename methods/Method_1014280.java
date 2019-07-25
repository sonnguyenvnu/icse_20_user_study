@Override public void initialize(){
  Bridge bridge=getBridge();
  DmxBridgeHandler bridgeHandler;
  if (bridge == null) {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"no bridge assigned");
    dmxHandlerStatus=ThingStatusDetail.CONFIGURATION_ERROR;
    return;
  }
 else {
    bridgeHandler=(DmxBridgeHandler)bridge.getHandler();
    if (bridgeHandler == null) {
      updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"no bridge handler available");
      dmxHandlerStatus=ThingStatusDetail.CONFIGURATION_ERROR;
      return;
    }
  }
  ChaserThingHandlerConfiguration configuration=getConfig().as(ChaserThingHandlerConfiguration.class);
  if (configuration.dmxid.isEmpty()) {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"DMX channel configuration missing");
    dmxHandlerStatus=ThingStatusDetail.CONFIGURATION_ERROR;
    return;
  }
  try {
    List<BaseDmxChannel> configChannels=BaseDmxChannel.fromString(configuration.dmxid,bridgeHandler.getUniverseId());
    logger.trace("found {} channels in {}",configChannels.size(),this.thing.getUID());
    for (    BaseDmxChannel channel : configChannels) {
      channels.add(bridgeHandler.getDmxChannel(channel,this.thing));
    }
  }
 catch (  IllegalArgumentException e) {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,e.getMessage());
    dmxHandlerStatus=ThingStatusDetail.CONFIGURATION_ERROR;
    return;
  }
  if (!configuration.steps.isEmpty()) {
    if (parseChaserConfig(configuration.steps)) {
      if (bridge.getStatus().equals(ThingStatus.ONLINE)) {
        updateStatus(ThingStatus.ONLINE);
        dmxHandlerStatus=ThingStatusDetail.NONE;
      }
 else {
        updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.BRIDGE_OFFLINE);
      }
    }
 else {
      updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"Chase configuration malformed");
      dmxHandlerStatus=ThingStatusDetail.CONFIGURATION_ERROR;
    }
  }
 else {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"Chase configuration missing");
    dmxHandlerStatus=ThingStatusDetail.CONFIGURATION_ERROR;
  }
  resumeAfter=configuration.resumeafter;
  logger.trace("set resumeAfter to {}",resumeAfter);
}
