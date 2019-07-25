@Override public void refresh(OwBaseBridgeHandler bridgeHandler,Boolean forcedRefresh) throws OwException {
  if (isConfigured) {
    if (enabledChannels.contains(CHANNEL_TEMPERATURE) || enabledChannels.contains(CHANNEL_HUMIDITY) || enabledChannels.contains(CHANNEL_ABSOLUTE_HUMIDITY) || enabledChannels.contains(CHANNEL_DEWPOINT)) {
      QuantityType<Temperature> temperature=new QuantityType<Temperature>((DecimalType)bridgeHandler.readDecimalType(sensorId,temperatureParameter),SIUnits.CELSIUS);
      logger.trace("read temperature {} from {}",temperature,sensorId);
      callback.postUpdate(CHANNEL_TEMPERATURE,temperature);
      if (enabledChannels.contains(CHANNEL_HUMIDITY) || enabledChannels.contains(CHANNEL_ABSOLUTE_HUMIDITY) || enabledChannels.contains(CHANNEL_DEWPOINT)) {
        QuantityType<Dimensionless> humidity=new QuantityType<Dimensionless>((DecimalType)bridgeHandler.readDecimalType(sensorId,humidityParameterR),SmartHomeUnits.PERCENT);
        logger.trace("read humidity {} from {}",humidity,sensorId);
        if (enabledChannels.contains(CHANNEL_HUMIDITY)) {
          callback.postUpdate(CHANNEL_HUMIDITY,humidity);
        }
        if (enabledChannels.contains(CHANNEL_ABSOLUTE_HUMIDITY)) {
          callback.postUpdate(CHANNEL_ABSOLUTE_HUMIDITY,Util.calculateAbsoluteHumidity(temperature,humidity));
        }
        if (enabledChannels.contains(CHANNEL_DEWPOINT)) {
          callback.postUpdate(CHANNEL_DEWPOINT,Util.calculateDewpoint(temperature,humidity));
        }
      }
    }
  }
}
