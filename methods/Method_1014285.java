@Override public void dispose(){
  if (channels.size() != 0) {
    channels.get(0).removeListener(new ChannelUID(this.thing.getUID(),CHANNEL_BRIGHTNESS_CW));
    channels.get(1).removeListener(new ChannelUID(this.thing.getUID(),CHANNEL_BRIGHTNESS_WW));
  }
  Bridge bridge=getBridge();
  if (bridge != null) {
    DmxBridgeHandler bridgeHandler=(DmxBridgeHandler)bridge.getHandler();
    if (bridgeHandler != null) {
      bridgeHandler.unregisterDmxChannels(this.thing);
      logger.debug("removing {} channels from {}",channels.size(),this.thing.getUID());
    }
  }
  channels.clear();
  currentValues.clear();
}
