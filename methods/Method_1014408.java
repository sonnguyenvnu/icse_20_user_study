@Override public void initialize(){
  super.initialize();
  logger.debug("Initialize OpenWeatherMapWeatherAndForecastHandler handler '{}'.",getThing().getUID());
  OpenWeatherMapWeatherAndForecastConfiguration config=getConfigAs(OpenWeatherMapWeatherAndForecastConfiguration.class);
  boolean configValid=true;
  int newForecastHours=config.getForecastHours();
  if (newForecastHours < 0 || newForecastHours > 120 || newForecastHours % 3 != 0) {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"@text/offline.conf-error-not-supported-number-of-hours");
    configValid=false;
  }
  int newForecastDays=config.getForecastDays();
  if (newForecastDays < 0 || newForecastDays > 16) {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"@text/offline.conf-error-not-supported-number-of-days");
    configValid=false;
  }
  if (configValid) {
    logger.debug("Rebuilding thing '{}'.",getThing().getUID());
    List<Channel> toBeAddedChannels=new ArrayList<>();
    List<Channel> toBeRemovedChannels=new ArrayList<>();
    if (forecastHours != newForecastHours) {
      logger.debug("Rebuilding hourly forecast channel groups.");
      if (forecastHours > newForecastHours) {
        for (int i=newForecastHours + 3; i <= forecastHours; i+=3) {
          toBeRemovedChannels.addAll(removeChannelsOfGroup(CHANNEL_GROUP_HOURLY_FORECAST_PREFIX + ((i < 10) ? "0" : "") + Integer.toString(i)));
        }
      }
 else {
        for (int i=forecastHours + 3; i <= newForecastHours; i+=3) {
          toBeAddedChannels.addAll(createChannelsForGroup(CHANNEL_GROUP_HOURLY_FORECAST_PREFIX + ((i < 10) ? "0" : "") + Integer.toString(i),CHANNEL_GROUP_TYPE_HOURLY_FORECAST));
        }
      }
      forecastHours=newForecastHours;
    }
    if (forecastDays != newForecastDays) {
      logger.debug("Rebuilding daily forecast channel groups.");
      if (forecastDays > newForecastDays) {
        if (newForecastDays < 1) {
          toBeRemovedChannels.addAll(removeChannelsOfGroup(CHANNEL_GROUP_FORECAST_TODAY));
        }
        if (newForecastDays < 2) {
          toBeRemovedChannels.addAll(removeChannelsOfGroup(CHANNEL_GROUP_FORECAST_TOMORROW));
        }
        for (int i=newForecastDays; i < forecastDays; ++i) {
          toBeRemovedChannels.addAll(removeChannelsOfGroup(CHANNEL_GROUP_DAILY_FORECAST_PREFIX + Integer.toString(i)));
        }
      }
 else {
        if (forecastDays == 0 && newForecastDays > 0) {
          toBeAddedChannels.addAll(createChannelsForGroup(CHANNEL_GROUP_FORECAST_TODAY,CHANNEL_GROUP_TYPE_DAILY_FORECAST));
        }
        if (forecastDays <= 1 && newForecastDays > 1) {
          toBeAddedChannels.addAll(createChannelsForGroup(CHANNEL_GROUP_FORECAST_TOMORROW,CHANNEL_GROUP_TYPE_DAILY_FORECAST));
        }
        for (int i=(forecastDays < 2) ? 2 : forecastDays; i < newForecastDays; ++i) {
          toBeAddedChannels.addAll(createChannelsForGroup(CHANNEL_GROUP_DAILY_FORECAST_PREFIX + Integer.toString(i),CHANNEL_GROUP_TYPE_DAILY_FORECAST));
        }
      }
      forecastDays=newForecastDays;
    }
    ThingBuilder builder=editThing().withoutChannels(toBeRemovedChannels);
    for (    Channel channel : toBeAddedChannels) {
      builder.withChannel(channel);
    }
    updateThing(builder.build());
  }
}
