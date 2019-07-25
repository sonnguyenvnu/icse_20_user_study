/** 
 * Create broker connections based on the service configuration. This will disconnect and discard all existing textual configured brokers.
 */
@Modified public void modified(@Nullable Map<String,Object> configMap){
  if (connection != null) {
    connection.stop();
  }
  if (configMap == null || configMap.isEmpty() || mqttService == null) {
    return;
  }
  final @NonNull MqttServiceImpl service=(@NonNull MqttServiceImpl)mqttService;
  MqttBrokerConnectionConfig config=new Configuration(configMap).as(MqttBrokerConnectionConfig.class);
  try {
    String brokerID=config.getBrokerID();
    if (StringUtils.isBlank(brokerID) || brokerID == null) {
      logger.warn("Ignore invalid broker connection configuration: {}",config);
      return;
    }
    MqttBrokerConnection c=service.addBrokerConnection(brokerID,config);
    connection=c;
    if (c == null) {
      logger.warn("Ignore existing broker connection configuration for: {}",brokerID);
      return;
    }
    c.start();
  }
 catch (  ConfigurationException|IllegalArgumentException e) {
    logger.warn("MqttBroker connection configuration faulty: {}",e.getMessage());
  }
catch (  MqttException e) {
    logger.warn("MqttBroker start failed: {}",e.getMessage(),e);
  }
}
