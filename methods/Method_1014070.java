@Modified protected synchronized void modified(Map<String,Object> config){
  logger.debug("Modifying the configuration of the firmware update service.");
  if (!isValid(config)) {
    return;
  }
  cancelFirmwareUpdateStatusInfoJob();
  firmwareStatusInfoJobPeriod=config.containsKey(PERIOD_CONFIG_KEY) ? (Integer)config.get(PERIOD_CONFIG_KEY) : firmwareStatusInfoJobPeriod;
  firmwareStatusInfoJobDelay=config.containsKey(DELAY_CONFIG_KEY) ? (Integer)config.get(DELAY_CONFIG_KEY) : firmwareStatusInfoJobDelay;
  firmwareStatusInfoJobTimeUnit=config.containsKey(TIME_UNIT_CONFIG_KEY) ? TimeUnit.valueOf((String)config.get(TIME_UNIT_CONFIG_KEY)) : firmwareStatusInfoJobTimeUnit;
  if (!firmwareUpdateHandlers.isEmpty()) {
    createFirmwareUpdateStatusInfoJob();
  }
}
