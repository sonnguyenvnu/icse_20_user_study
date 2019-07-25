@Override public synchronized void release(){
  this.thingTypeProvider.removeAll(bundle);
  this.channelGroupTypeProvider.removeAll(bundle);
  this.channelTypeProvider.removeAll(bundle);
  this.configDescriptionProvider.removeAll(bundle);
}
