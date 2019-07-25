@Override public void initialize(){
  logger.debug("initializing ArtNet bridge {}",this.thing.getUID());
  packetTemplate=new ArtnetPacket();
  updateConfiguration();
}
