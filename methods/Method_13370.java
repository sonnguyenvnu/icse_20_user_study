private void setServerPort(ConfigurableEnvironment environment,String serverPort,Map<String,Object> defaultProperties){
  if (serverPort == null) {
    return;
  }
  defaultProperties.put(SERVER_PORT_PROPERTY_NAME,serverPort);
}
