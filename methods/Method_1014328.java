@Override public void generate(HmDevice device){
  if (thingTypeProvider != null) {
    ThingTypeUID thingTypeUID=UidUtils.generateThingTypeUID(device);
    ThingType tt=thingTypeProvider.getInternalThingType(thingTypeUID);
    if (tt == null || device.isGatewayExtras()) {
      logger.debug("Generating ThingType for device '{}' with {} datapoints",device.getType(),device.getDatapointCount());
      List<ChannelGroupType> groupTypes=new ArrayList<ChannelGroupType>();
      for (      HmChannel channel : device.getChannels()) {
        List<ChannelDefinition> channelDefinitions=new ArrayList<ChannelDefinition>();
        if (!channel.isReconfigurable()) {
          for (          HmDatapoint dp : channel.getDatapoints()) {
            if (!isIgnoredDatapoint(dp) && dp.getParamsetType() == HmParamsetType.VALUES) {
              ChannelTypeUID channelTypeUID=UidUtils.generateChannelTypeUID(dp);
              ChannelType channelType=channelTypeProvider.getInternalChannelType(channelTypeUID);
              if (channelType == null) {
                channelType=createChannelType(dp,channelTypeUID);
                channelTypeProvider.addChannelType(channelType);
              }
              ChannelDefinition channelDef=new ChannelDefinitionBuilder(dp.getName(),channelType.getUID()).build();
              channelDefinitions.add(channelDef);
            }
          }
        }
        ChannelGroupTypeUID groupTypeUID=UidUtils.generateChannelGroupTypeUID(channel);
        ChannelGroupType groupType=channelGroupTypeProvider.getInternalChannelGroupType(groupTypeUID);
        if (groupType == null || device.isGatewayExtras()) {
          String groupLabel=String.format("%s",WordUtils.capitalizeFully(StringUtils.replace(channel.getType(),"_"," ")));
          groupType=ChannelGroupTypeBuilder.instance(groupTypeUID,groupLabel).withChannelDefinitions(channelDefinitions).build();
          channelGroupTypeProvider.addChannelGroupType(groupType);
          groupTypes.add(groupType);
        }
      }
      tt=createThingType(device,groupTypes);
      thingTypeProvider.addThingType(tt);
    }
    addFirmware(device);
  }
}
