@SuppressWarnings("null") public void initialize(ServiceConfiguration config) throws IOException {
  int port=config.port == null ? (config.port=config.secure ? 8883 : 1883) : config.port;
  connection=service.getBrokerConnection(Constants.CLIENTID);
  if (connection != null) {
    connection.stop();
    service.removeBrokerConnection(Constants.CLIENTID);
  }
  connection=new MqttBrokerConnection("127.0.0.1",config.port,config.secure,Constants.CLIENTID);
  connection.addConnectionObserver(this);
  if (config.username != null) {
    connection.setCredentials(config.username,config.password);
  }
  startEmbeddedServer(port,config.secure,config.username,config.password,config.persistenceFile);
}
