@Override protected void stop(){
  channelStateByChannelUID.values().forEach(c -> c.getCache().resetState());
}
