@Override public void initialize(){
  TradfriGatewayConfig configuration=getConfigAs(TradfriGatewayConfig.class);
  if (isNullOrEmpty(configuration.host)) {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"Host must be specified in the configuration!");
    return;
  }
  if (isNullOrEmpty(configuration.code)) {
    if (isNullOrEmpty(configuration.identity) || isNullOrEmpty(configuration.preSharedKey)) {
      updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"Either security code or identity and pre-shared key must be provided in the configuration!");
      return;
    }
 else {
      establishConnection();
    }
  }
 else {
    String currentFirmware=thing.getProperties().get(Thing.PROPERTY_FIRMWARE_VERSION);
    if (isNullOrEmpty(currentFirmware) || MIN_SUPPORTED_VERSION.compareTo(new TradfriVersion(currentFirmware)) > 0) {
      if (!isNullOrEmpty(currentFirmware)) {
        logger.warn("Gateway with old firmware '{}' - please consider upgrading to the latest version.",currentFirmware);
      }
      Configuration editedConfig=editConfiguration();
      editedConfig.put(TradfriBindingConstants.GATEWAY_CONFIG_IDENTITY,"");
      editedConfig.put(TradfriBindingConstants.GATEWAY_CONFIG_PRE_SHARED_KEY,configuration.code);
      updateConfiguration(editedConfig);
      establishConnection();
    }
 else {
      scheduler.execute(() -> {
        boolean success=obtainIdentityAndPreSharedKey();
        if (success) {
          establishConnection();
        }
      }
);
    }
  }
}
