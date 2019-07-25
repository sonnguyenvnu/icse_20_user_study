@Override public void refresh(OwBaseBridgeHandler bridgeHandler,Boolean forcedRefresh) throws OwException {
  if (isConfigured) {
    double Vcc=5.0;
    if (enabledChannels.contains(CHANNEL_TEMPERATURE) || enabledChannels.contains(CHANNEL_HUMIDITY) || enabledChannels.contains(CHANNEL_ABSOLUTE_HUMIDITY) || enabledChannels.contains(CHANNEL_DEWPOINT)) {
      QuantityType<Temperature> temperature=new QuantityType<Temperature>((DecimalType)bridgeHandler.readDecimalType(sensorId,temperatureParameter),SIUnits.CELSIUS);
      logger.trace("read temperature {} from {}",temperature,sensorId);
      if (enabledChannels.contains(CHANNEL_TEMPERATURE)) {
        callback.postUpdate(CHANNEL_TEMPERATURE,temperature);
      }
      if (enabledChannels.contains(CHANNEL_HUMIDITY) || enabledChannels.contains(CHANNEL_ABSOLUTE_HUMIDITY) || enabledChannels.contains(CHANNEL_DEWPOINT)) {
        QuantityType<Dimensionless> humidity=new QuantityType<Dimensionless>((DecimalType)bridgeHandler.readDecimalType(sensorId,humidityParameter),SmartHomeUnits.PERCENT);
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
    if (enabledChannels.contains(CHANNEL_VOLTAGE)) {
      State voltage=new QuantityType<ElectricPotential>((DecimalType)bridgeHandler.readDecimalType(sensorId,voltageParameter),SmartHomeUnits.VOLT);
      logger.trace("read voltage {} from {}",voltage,sensorId);
      callback.postUpdate(CHANNEL_VOLTAGE,voltage);
    }
    if (enabledChannels.contains(CHANNEL_CURRENT)) {
      if (currentSensorType == CurrentSensorType.IBUTTONLINK) {
        State current=bridgeHandler.readDecimalType(sensorId,voltageParameter);
        if (current instanceof DecimalType) {
          double currentDouble=((DecimalType)current).doubleValue();
          if (currentDouble >= 0.1 || currentDouble <= 3.78) {
            current=new QuantityType<ElectricCurrent>(currentDouble * 5.163 + 0.483,SmartHomeUnits.AMPERE);
          }
          callback.postUpdate(CHANNEL_CURRENT,current);
        }
 else {
          callback.postUpdate(CHANNEL_CURRENT,UnDefType.UNDEF);
        }
      }
 else {
        State current=new QuantityType<ElectricCurrent>((DecimalType)bridgeHandler.readDecimalType(sensorId,currentParamater),MILLI(SmartHomeUnits.AMPERE));
        callback.postUpdate(CHANNEL_CURRENT,current);
      }
    }
    if (enabledChannels.contains(CHANNEL_SUPPLYVOLTAGE)) {
      Vcc=((DecimalType)bridgeHandler.readDecimalType(sensorId,supplyVoltageParameter)).doubleValue();
      State supplyVoltage=new QuantityType<ElectricPotential>(Vcc,SmartHomeUnits.VOLT);
      callback.postUpdate(CHANNEL_SUPPLYVOLTAGE,supplyVoltage);
    }
    if (enabledChannels.contains(CHANNEL_LIGHT)) {
switch (lightSensorType) {
case ELABNET_V2:
        State light=bridgeHandler.readDecimalType(sensorId,currentParamater);
      if (light instanceof DecimalType) {
        light=new QuantityType<Illuminance>(Math.round(Math.pow(10,((DecimalType)light).doubleValue() / 47 * 1000)),SmartHomeUnits.LUX);
        callback.postUpdate(CHANNEL_LIGHT,light);
      }
    break;
case ELABNET_V1:
  light=bridgeHandler.readDecimalType(sensorId,currentParamater);
if (light instanceof DecimalType) {
  light=new QuantityType<Illuminance>(Math.round(Math.exp(1.059 * Math.log(1000000 * ((DecimalType)light).doubleValue() / (4096 * 390)) + 4.518) * 20000),SmartHomeUnits.LUX);
  callback.postUpdate(CHANNEL_LIGHT,light);
}
break;
case IBUTTONLINK:
light=bridgeHandler.readDecimalType(sensorId,voltageParameter);
if (light instanceof DecimalType) {
light=new QuantityType<Illuminance>(Math.pow(10,(65 / 7.5) - (47 / 7.5) * (Vcc / ((DecimalType)light).doubleValue())),SmartHomeUnits.LUX);
callback.postUpdate(CHANNEL_LIGHT,light);
}
}
}
}
}
