@Override public void refresh(OwBaseBridgeHandler bridgeHandler,Boolean forcedRefresh) throws OwException {
  if (isConfigured && enabledChannels.contains(CHANNEL_TEMPERATURE)) {
    QuantityType<Temperature> temperature=new QuantityType<Temperature>((DecimalType)bridgeHandler.readDecimalType(sensorId,temperatureParamater),SIUnits.CELSIUS);
    logger.trace("read temperature {} from {}",temperature,sensorId);
    if (ignorePOR && (Double.compare(temperature.doubleValue(),85.0) == 0)) {
      logger.trace("ignored POR value from sensor {}",sensorId);
    }
 else {
      callback.postUpdate(CHANNEL_TEMPERATURE,temperature);
    }
  }
}
