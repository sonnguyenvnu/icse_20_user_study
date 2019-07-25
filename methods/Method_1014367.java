/** 
 * Return the channel group type for this Node.
 */
public ChannelGroupType type(){
  final List<ChannelDefinition> channelDefinitions=properties.stream().map(c -> new ChannelDefinitionBuilder(c.propertyID,c.channelTypeUID).build()).collect(Collectors.toList());
  return ChannelGroupTypeBuilder.instance(channelGroupTypeUID,attributes.name).withChannelDefinitions(channelDefinitions).build();
}
