public void activate(){
  logger.debug("Starting UPnP IO service...");
  upnpService.getRegistry().getRemoteDevices().forEach(device -> informParticipants(device,true));
  upnpService.getRegistry().addListener(this);
}
