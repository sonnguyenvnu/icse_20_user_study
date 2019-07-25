/** 
 * Callback of  {@link DelayedBatchProcessing}. Add all newly discovered components to the Thing and start the components.
 */
@SuppressWarnings("null") @Override public void accept(List<AbstractComponent> discoveredComponentsList){
  MqttBrokerConnection connection=this.connection;
  if (connection == null) {
    return;
  }
  List<Channel> channels=new ArrayList<>();
synchronized (haComponents) {
    for (    AbstractComponent discovered : discoveredComponentsList) {
      AbstractComponent known=haComponents.get(discovered.uid().getId());
      if (known != null) {
        if (discovered.getConfigHash() != known.getConfigHash()) {
          known.stop();
        }
 else {
          continue;
        }
      }
      channelTypeProvider.setChannelGroupType(discovered.groupTypeUID(),discovered.type());
      discovered.addChannelTypes(channelTypeProvider);
      haComponents.put(discovered.uid().getId(),discovered);
      discovered.start(connection,scheduler,0).exceptionally(e -> {
        logger.warn("Failed to start component {}",discovered.uid(),e);
        return null;
      }
);
    }
    for (    AbstractComponent e : haComponents.values()) {
      for (      CChannel entry : e.channelTypes().values()) {
        channels.add(entry.channel);
      }
    }
  }
  updateThing(editThing().withChannels(channels).build());
  updateStatus(ThingStatus.ONLINE);
}
