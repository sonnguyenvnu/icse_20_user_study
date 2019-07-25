@Override public void initialize(){
  logger.debug("Initializing thing {}",getThing().getUID());
  String thingUid=getThing().getUID().toString();
  thingConfig=getConfigAs(AstroThingConfig.class);
  thingConfig.setThingUid(thingUid);
  boolean validConfig=true;
  if (StringUtils.trimToNull(thingConfig.getGeolocation()) == null) {
    logger.error("Astro parameter geolocation is mandatory and must be configured, disabling thing '{}'",thingUid);
    validConfig=false;
  }
 else {
    thingConfig.parseGeoLocation();
  }
  if (thingConfig.getLatitude() == null || thingConfig.getLongitude() == null) {
    logger.error("Astro parameters geolocation could not be split into latitude and longitude, disabling thing '{}'",thingUid);
    validConfig=false;
  }
  if (thingConfig.getInterval() == null || thingConfig.getInterval() < 1 || thingConfig.getInterval() > 86400) {
    logger.error("Astro parameter interval must be in the range of 1-86400, disabling thing '{}'",thingUid);
    validConfig=false;
  }
  if (validConfig) {
    logger.debug("{}",thingConfig);
    updateStatus(ONLINE);
    restartJobs();
  }
 else {
    updateStatus(OFFLINE);
  }
  logger.debug("Thing {} initialized {}",getThing().getUID(),getThing().getStatus());
}
