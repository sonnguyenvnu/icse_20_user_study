@Override public void initialize(){
  logger.debug("initializing Lib485 bridge {}",this.thing.getUID());
  updateConfiguration();
}
