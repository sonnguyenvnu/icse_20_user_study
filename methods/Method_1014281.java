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
  ColorThingHandlerConfiguration configuration=getConfig().as(ColorThingHandlerConfiguration.class);
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
  currentValues.add(DmxChannel.MIN_VALUE);
  currentValues.add(DmxChannel.MIN_VALUE);
  currentValues.add(DmxChannel.MIN_VALUE);
  fadeTime=configuration.fadetime;
  logger.trace("setting fadeTime to {} ms in {}",fadeTime,this.thing.getUID());
  dimTime=configuration.dimtime;
  logger.trace("setting dimTime to {} ms in {}",fadeTime,this.thing.getUID());
  String turnOnValueString=String.valueOf(fadeTime) + ":" + configuration.turnonvalue + ":-1";
  ValueSet turnOnValue=ValueSet.fromString(turnOnValueString);
  if (turnOnValue.size() % 3 == 0) {
    this.turnOnValue=turnOnValue;
    logger.trace("set turnonvalue to {} in {}",turnOnValue,this.thing.getUID());
  }
 else {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"turn-on value malformed");
    dmxHandlerStatus=ThingStatusDetail.CONFIGURATION_ERROR;
    return;
  }
  this.turnOnValue.setFadeTime(fadeTime);
  dynamicTurnOnValue=configuration.dynamicturnonvalue;
  String turnOffValueString=String.valueOf(fadeTime) + ":" + configuration.turnoffvalue + ":-1";
  ValueSet turnOffValue=ValueSet.fromString(turnOffValueString);
  if (turnOffValue.size() % 3 == 0) {
    this.turnOffValue=turnOffValue;
    logger.trace("set turnoffvalue to {} in {}",turnOffValue,this.thing.getUID());
  }
 else {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"turn-off value malformed");
    dmxHandlerStatus=ThingStatusDetail.CONFIGURATION_ERROR;
    return;
  }
  this.turnOffValue.setFadeTime(fadeTime);
  channels.get(0).addListener(new ChannelUID(this.thing.getUID(),CHANNEL_BRIGHTNESS_R),this,ListenerType.VALUE);
  channels.get(1).addListener(new ChannelUID(this.thing.getUID(),CHANNEL_BRIGHTNESS_G),this,ListenerType.VALUE);
  channels.get(2).addListener(new ChannelUID(this.thing.getUID(),CHANNEL_BRIGHTNESS_B),this,ListenerType.VALUE);
  if (bridge.getStatus().equals(ThingStatus.ONLINE)) {
    updateStatus(ThingStatus.ONLINE);
    dmxHandlerStatus=ThingStatusDetail.NONE;
  }
 else {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.BRIDGE_OFFLINE);
  }
}
