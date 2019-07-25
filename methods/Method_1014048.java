/** 
 * Creates a channel builder from the given channel.
 * @param channel the channel to be changed
 * @return channel builder
 */
public static ChannelBuilder create(Channel channel){
  ChannelBuilder channelBuilder=create(channel.getUID(),channel.getAcceptedItemType()).withConfiguration(channel.getConfiguration()).withDefaultTags(channel.getDefaultTags()).withKind(channel.getKind()).withProperties(channel.getProperties()).withType(channel.getChannelTypeUID());
  String label=channel.getLabel();
  if (label != null) {
    channelBuilder.withLabel(label);
  }
  String description=channel.getDescription();
  if (description != null) {
    channelBuilder.withDescription(description);
  }
  return channelBuilder;
}
