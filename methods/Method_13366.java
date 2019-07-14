/** 
 * Reset server port property if it's absent, whose value is configured by "dubbbo.protocol.port" or "dubbo.protcols.rest.port"
 * @param environment
 * @param defaultProperties
 */
private void resetServerPort(ConfigurableEnvironment environment,Map<String,Object> defaultProperties){
  String serverPort=environment.getProperty(SERVER_PORT_PROPERTY_NAME,environment.getProperty(PORT_PROPERTY_NAME));
  if (serverPort != null) {
    return;
  }
  serverPort=getRestPortFromProtocolProperty(environment);
  if (serverPort == null) {
    serverPort=getRestPortFromProtocolsProperties(environment);
  }
  setServerPort(environment,serverPort,defaultProperties);
}
