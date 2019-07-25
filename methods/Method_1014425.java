@Override public void initialize(){
  logger.debug("Initializing weatherunderground bridge handler.");
  Configuration config=getThing().getConfiguration();
  if (StringUtils.trimToNull((String)config.get(WeatherUndergroundBindingConstants.APIKEY)) == null) {
    logger.debug("Setting thing '{}' to OFFLINE: Parameter 'apikey' must be configured.",getThing().getUID());
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"@text/offline.conf-error-missing-apikey");
  }
 else {
    apikey=(String)config.get(WeatherUndergroundBindingConstants.APIKEY);
    updateStatus(ThingStatus.UNKNOWN);
    startControlApiKeyJob();
  }
}
