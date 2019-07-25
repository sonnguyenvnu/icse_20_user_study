/** 
 * Initialize the bridge.
 */
@Override public void initialize(){
  logger.debug("Initializing meteoblue bridge");
  MeteoBlueBridgeConfig config=getConfigAs(MeteoBlueBridgeConfig.class);
  String apiKeyTemp=config.getApiKey();
  if (StringUtils.isBlank(apiKeyTemp)) {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"Cannot initialize meteoblue bridge. No apiKey provided.");
    return;
  }
  apiKey=apiKeyTemp;
  healthCheck();
}
