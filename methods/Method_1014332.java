@Override public void initialize(){
  logger.debug("Initializing hue bridge handler.");
  hueBridgeConfig=getConfigAs(HueBridgeConfig.class);
  if (hueBridgeConfig.getIpAddress() == null || hueBridgeConfig.getIpAddress().isEmpty()) {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.OFFLINE.CONFIGURATION_ERROR,"@text/offline.conf-error-no-ip-address");
  }
 else {
    if (hueBridge == null) {
      hueBridge=new HueBridge(hueBridgeConfig.getIpAddress(),scheduler);
      hueBridge.setTimeout(5000);
    }
    onUpdate();
  }
}
