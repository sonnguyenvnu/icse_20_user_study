@Override public void initialize(){
  try {
    logger.debug("Initializing NTP handler for '{}'.",getThing().getUID());
    Configuration config=getThing().getConfiguration();
    hostname=config.get(PROPERTY_NTP_SERVER_HOST).toString();
    port=(BigDecimal)config.get(PROPERTY_NTP_SERVER_PORT);
    refreshInterval=(BigDecimal)config.get(PROPERTY_REFRESH_INTERVAL);
    refreshNtp=(BigDecimal)config.get(PROPERTY_REFRESH_NTP);
    refreshNtpCount=0;
    try {
      Object timeZoneConfigValue=config.get(PROPERTY_TIMEZONE);
      if (timeZoneConfigValue != null) {
        timeZone=TimeZone.getTimeZone(timeZoneConfigValue.toString());
      }
 else {
        timeZone=TimeZone.getDefault();
        logger.debug("{} using default TZ '{}', because configuration property '{}' is null.",getThing().getUID(),timeZone,PROPERTY_TIMEZONE);
      }
    }
 catch (    Exception e) {
      timeZone=TimeZone.getDefault();
      logger.debug("{} using default TZ '{}' due to an occurred exception: ",getThing().getUID(),timeZone,e);
    }
    try {
      Object localeStringConfigValue=config.get(PROPERTY_LOCALE);
      if (localeStringConfigValue != null) {
        locale=new Locale(localeStringConfigValue.toString());
      }
 else {
        locale=localeProvider.getLocale();
        logger.debug("{} using default locale '{}', because configuration property '{}' is null.",getThing().getUID(),locale,PROPERTY_LOCALE);
      }
    }
 catch (    Exception e) {
      locale=localeProvider.getLocale();
      logger.debug("{} using default locale '{}' due to an occurred exception: ",getThing().getUID(),locale,e);
    }
    dateTimeChannelUID=new ChannelUID(getThing().getUID(),CHANNEL_DATE_TIME);
    stringChannelUID=new ChannelUID(getThing().getUID(),CHANNEL_STRING);
    try {
      Channel stringChannel=getThing().getChannel(stringChannelUID.getId());
      if (stringChannel != null) {
        Configuration cfg=stringChannel.getConfiguration();
        String dateTimeFormatString=cfg.get(PROPERTY_DATE_TIME_FORMAT).toString();
        if (!(dateTimeFormatString == null || dateTimeFormatString.isEmpty())) {
          dateTimeFormat=DateTimeFormatter.ofPattern(dateTimeFormatString);
        }
 else {
          logger.debug("No format set in channel config for {}. Using default format.",stringChannelUID);
          dateTimeFormat=DateTimeFormatter.ofPattern(DATE_PATTERN_WITH_TZ);
        }
      }
 else {
        logger.debug("Missing channel: '{}'",stringChannelUID.getId());
      }
    }
 catch (    RuntimeException ex) {
      logger.debug("No channel config or invalid format for {}. Using default format. ({})",stringChannelUID,ex.getMessage());
      dateTimeFormat=DateTimeFormatter.ofPattern(DATE_PATTERN_WITH_TZ);
    }
    SDF.setTimeZone(timeZone);
    dateTimeFormat.withZone(timeZone.toZoneId());
    logger.debug("Initialized NTP handler '{}' with configuration: host '{}', refresh interval {}, timezone {}, locale {}.",getThing().getUID(),hostname,refreshInterval,timeZone,locale);
    startAutomaticRefresh();
  }
 catch (  Exception ex) {
    logger.error("Error occurred while initializing NTP handler: {}",ex.getMessage(),ex);
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"@text/offline.conf-error-init-handler");
  }
}
