@Override public void initialize(){
  super.initialize();
  logger.debug("Initialize OpenWeatherMapUVIndexHandler handler '{}'.",getThing().getUID());
  OpenWeatherMapUVIndexConfiguration config=getConfigAs(OpenWeatherMapUVIndexConfiguration.class);
  boolean configValid=true;
  int newForecastDays=config.getForecastDays();
  if (newForecastDays < 1 || newForecastDays > 8) {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"@text/offline.conf-error-not-supported-uvindex-number-of-days");
    configValid=false;
  }
  if (configValid) {
    logger.debug("Rebuilding thing '{}'.",getThing().getUID());
    List<Channel> toBeAddedChannels=new ArrayList<>();
    List<Channel> toBeRemovedChannels=new ArrayList<>();
    if (forecastDays != newForecastDays) {
      logger.debug("Rebuilding UV index channel groups.");
      if (forecastDays > newForecastDays) {
        if (newForecastDays < 2) {
          toBeRemovedChannels.addAll(removeChannelsOfGroup(CHANNEL_GROUP_FORECAST_TOMORROW));
        }
        for (int i=newForecastDays; i < forecastDays; ++i) {
          toBeRemovedChannels.addAll(removeChannelsOfGroup(CHANNEL_GROUP_FORECAST_PREFIX + Integer.toString(i)));
        }
      }
 else {
        if (forecastDays <= 1 && newForecastDays > 1) {
          toBeAddedChannels.addAll(createChannelsForGroup(CHANNEL_GROUP_FORECAST_TOMORROW,CHANNEL_GROUP_TYPE_UVINDEX));
        }
        for (int i=(forecastDays < 2) ? 2 : forecastDays; i < newForecastDays; ++i) {
          toBeAddedChannels.addAll(createChannelsForGroup(CHANNEL_GROUP_FORECAST_PREFIX + Integer.toString(i),CHANNEL_GROUP_TYPE_UVINDEX));
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
