/** 
 * Maps channel into channel DTO object.
 * @param channel the channel
 * @return the channel DTO object
 */
public static ChannelDTO map(Channel channel){
  ChannelTypeUID channelTypeUID=channel.getChannelTypeUID();
  String channelTypeUIDValue=channelTypeUID != null ? channelTypeUID.toString() : null;
  return new ChannelDTO(channel.getUID(),channelTypeUIDValue,channel.getAcceptedItemType(),channel.getKind(),channel.getLabel(),channel.getDescription(),channel.getProperties(),channel.getConfiguration(),channel.getDefaultTags());
}
