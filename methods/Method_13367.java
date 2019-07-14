private String getRestPortFromProtocolProperty(ConfigurableEnvironment environment){
  String protocol=environment.getProperty(PROTOCOL_NAME_PROPERTY_NAME,DEFAULT_PROTOCOL);
  return isRestProtocol(protocol) ? environment.getProperty(PROTOCOL_PORT_PROPERTY_NAME) : null;
}
