@Override protected CompletableFuture<@Nullable Void> start(MqttBrokerConnection connection){
  connection.setRetain(true);
  connection.setQos(1);
  return device.subscribe(connection,scheduler,attributeReceiveTimeout);
}
