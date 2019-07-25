@Override public void initialize(){
  logger.debug("initializing sACN/E1.31 bridge {}",this.thing.getUID());
  packetTemplate=new SacnPacket(senderUUID);
  updateConfiguration();
}
