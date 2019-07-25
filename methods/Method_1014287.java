@Override public void initialize(){
  final String ip=(String)getThing().getConfiguration().get(CONFIG_PROPERTY_IP);
  final BigDecimal port=(BigDecimal)getThing().getConfiguration().get(CONFIG_PROPERTY_PORT);
  final String pin=(String)getThing().getConfiguration().get(CONFIG_PROPERTY_PIN);
  if (ip == null || StringUtils.isEmpty(pin) || port.intValue() == 0) {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"Configuration incomplete");
  }
 else {
    radio=new FrontierSiliconRadio(ip,port.intValue(),pin,httpClient);
    logger.debug("Initializing connection to {}:{}",ip,port);
    radioLogin();
    final BigDecimal period=(BigDecimal)getThing().getConfiguration().get(CONFIG_PROPERTY_REFRESH);
    if (period != null && period.intValue() > 0) {
      updateJob=scheduler.scheduleWithFixedDelay(updateRunnable,period.intValue(),period.intValue(),SECONDS);
    }
  }
}
