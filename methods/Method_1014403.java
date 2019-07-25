public boolean initialize(ChannelUID channelUID,String acceptedItemType){
  this.channelUID=channelUID;
  this.acceptedItemType=acceptedItemType;
  refreshCycle=refresh.intValue() * 1000;
  return !path.isEmpty();
}
