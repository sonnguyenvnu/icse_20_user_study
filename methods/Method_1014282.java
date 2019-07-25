@Override public void dispose(){
  if (channels.size() != 0) {
    channels.get(0).removeListener(new ChannelUID(this.thing.getUID(),CHANNEL_BRIGHTNESS_R));
    channels.get(1).removeListener(new ChannelUID(this.thing.getUID(),CHANNEL_BRIGHTNESS_G));
    channels.get(2).removeListener(new ChannelUID(this.thing.getUID(),CHANNEL_BRIGHTNESS_B));
  }
  channels.clear();
  currentValues.clear();
  currentColor=new HSBType();
}
