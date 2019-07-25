void shutdown(){
  for (  ChannelWrapper cw : channelTables.values()) {
    this.closeChannel(cw.getChannel());
  }
  channelTables.clear();
}
