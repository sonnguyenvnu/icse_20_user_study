@Override public void refresh(OwBaseBridgeHandler bridgeHandler,Boolean forcedRefresh) throws OwException {
  if (isConfigured) {
    if (enabledChannels.contains(CHANNEL_TEMPERATURE) || enabledChannels.contains(CHANNEL_HUMIDITY) || enabledChannels.contains(CHANNEL_ABSOLUTE_HUMIDITY) || enabledChannels.contains(CHANNEL_DEWPOINT)) {
      QuantityType<Temperature> temperature=new QuantityType<>((DecimalType)bridgeHandler.readDecimalType(sensorId,temperatureParameter),SIUnits.CELSIUS);
      logger.trace("read temperature {} from {}",temperature,sensorId);
      if (enabledChannels.contains(CHANNEL_TEMPERATURE)) {
        callback.postUpdate(CHANNEL_TEMPERATURE,temperature);
      }
      if (enabledChannels.contains(CHANNEL_HUMIDITY) || enabledChannels.contains(CHANNEL_ABSOLUTE_HUMIDITY) || enabledChannels.contains(CHANNEL_DEWPOINT)) {
        QuantityType<Dimensionless> humidity=new QuantityType<>((DecimalType)bridgeHandler.readDecimalType(sensorId,humidityParameter),SmartHomeUnits.PERCENT);
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
    if (enabledChannels.contains(CHANNEL_LIGHT)) {
      QuantityType<Illuminance> light=new QuantityType<>((DecimalType)bridgeHandler.readDecimalType(sensorId,lightParameter),SmartHomeUnits.LUX);
      logger.trace("read light {} from {}",light,sensorId);
      callback.postUpdate(CHANNEL_LIGHT,light);
    }
    if (enabledChannels.contains(CHANNEL_PRESSURE)) {
      QuantityType<Pressure> pressure=new QuantityType<>((DecimalType)bridgeHandler.readDecimalType(sensorId,pressureParameter),MetricPrefix.HECTO(SIUnits.PASCAL));
      logger.trace("read pressure {} from {}",pressure,sensorId);
      callback.postUpdate(CHANNEL_PRESSURE,pressure);
    }
  }
}
