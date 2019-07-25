/** 
 * Callback of  {@link DelayedBatchProcessing}. Add all newly discovered nodes and properties to the Thing and start subscribe to each channel state topic.
 */
@Override public void accept(@Nullable List<Object> t){
  if (!device.isInitialized()) {
    return;
  }
  List<Channel> channels=device.nodes().stream().flatMap(n -> n.properties.stream()).map(prop -> prop.getChannel()).collect(Collectors.toList());
  updateThing(editThing().withChannels(channels).build());
  updateProperty(MqttBindingConstants.HOMIE_PROPERTY_VERSION,device.attributes.homie);
  final MqttBrokerConnection connection=this.connection;
  if (connection != null) {
    device.startChannels(connection,scheduler,attributeReceiveTimeout,this).thenRun(() -> {
      logger.trace("Homie device {} fully attached",device.attributes.name);
    }
);
  }
}
