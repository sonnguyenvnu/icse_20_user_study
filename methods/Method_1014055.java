/** 
 * Maps channel DTO into channel object.
 * @param channelDTO the channel DTO
 * @return the channel object
 */
public static Channel map(ChannelDTO channelDTO){
  ChannelUID channelUID=new ChannelUID(channelDTO.uid);
  ChannelTypeUID channelTypeUID=new ChannelTypeUID(channelDTO.channelTypeUID);
  return ChannelBuilder.create(channelUID,channelDTO.itemType).withConfiguration(new Configuration(channelDTO.configuration)).withLabel(channelDTO.label).withDescription(channelDTO.description).withProperties(channelDTO.properties).withType(channelTypeUID).withDefaultTags(channelDTO.defaultTags).withKind(ChannelKind.parse(channelDTO.kind)).build();
}
