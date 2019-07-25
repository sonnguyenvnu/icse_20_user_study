@Override public void deactivate(){
  logger.debug("Stopping WeMo UPnP discovery...");
  stopScan();
}
