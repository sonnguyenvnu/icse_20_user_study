@Override public void initialize(){
  List<ChannelUID> configErrors=new ArrayList<>();
  for (  Channel channel : thing.getChannels()) {
    final ChannelTypeUID channelTypeUID=channel.getChannelTypeUID();
    if (channelTypeUID == null) {
      logger.warn("Channel {} has no type",channel.getLabel());
      continue;
    }
    final ChannelConfig channelConfig=channel.getConfiguration().as(ChannelConfig.class);
    try {
      Value value=ValueFactory.createValueState(channelConfig,channelTypeUID.getId());
      ChannelState channelState=createChannelState(channelConfig,channel.getUID(),value);
      channelStateByChannelUID.put(channel.getUID(),channelState);
      StateDescription description=value.createStateDescription(channelConfig.unit,StringUtils.isBlank(channelConfig.commandTopic));
      stateDescProvider.setDescription(channel.getUID(),description);
    }
 catch (    IllegalArgumentException e) {
      logger.warn("Channel configuration error",e);
      configErrors.add(channel.getUID());
    }
  }
  if (configErrors.isEmpty()) {
    super.initialize();
  }
 else {
    updateStatus(ThingStatus.OFFLINE,ThingStatusDetail.CONFIGURATION_ERROR,"Remove and recreate: " + configErrors.stream().map(e -> e.getAsString()).collect(Collectors.joining(",")));
  }
}
